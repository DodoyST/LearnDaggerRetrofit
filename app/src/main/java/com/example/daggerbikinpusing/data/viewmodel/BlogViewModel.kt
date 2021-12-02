package com.example.daggerbikinpusing.data.viewmodel

import androidx.lifecycle.*
import com.example.daggerbikinpusing.R
import com.example.daggerbikinpusing.data.model.Blog
import com.example.daggerbikinpusing.data.repository.BlogRepository
import com.example.daggerbikinpusing.util.AppResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class BlogViewModel @Inject constructor(private val blogRepository: BlogRepository) : ViewModel() {
  
  private var _blog = MutableLiveData<AppResource<Blog>>()
  val blog: LiveData<AppResource<Blog>>
    get() = _blog
  
  fun getAll() = liveData(Dispatchers.IO) {
    emit(AppResource.Loading)
    try {
      val response = blogRepository.getAllBlog()
      if (response.isSuccessful) emit(AppResource.Success(response.body()))
      else emit(AppResource.Error(null, response.errorBody().toString()))
    } catch (e: Exception) {
      emit(AppResource.Error(null, e.message ?: R.string.error_occurred.toString()))
    }
  }
  
  fun getById(id: String) = viewModelScope.launch(Dispatchers.IO) {
    _blog.postValue(AppResource.Loading)
    try {
      val response = blogRepository.getBlogById(id)
      if (response.isSuccessful) _blog.postValue(AppResource.Success(response.body()))
      else _blog.postValue(AppResource.Error(null, response.errorBody().toString()))
    } catch (e: Exception) {
      _blog.postValue(AppResource.Error(null, e.message ?: R.string.error_occurred.toString()))
    }
  }
}
