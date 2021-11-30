package com.example.daggerbikinpusing.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.daggerbikinpusing.R
import com.example.daggerbikinpusing.data.model.Login
import com.example.daggerbikinpusing.data.repository.LoginRepository
import com.example.daggerbikinpusing.util.AppResource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
  ViewModel() {
  fun login(login: Login) = liveData(Dispatchers.IO) {
    emit(AppResource.Loading)
    try {
      val response = loginRepository.login(login)
      if (response.isSuccessful) {
        emit(AppResource.Success(response.body()))
      } else {
        emit(AppResource.Error(null, response.errorBody().toString()))
      }
    } catch (e: Exception) {
      emit(AppResource.Error(null, e.message ?: R.string.error_occurred.toString()))
    }
  }
}
