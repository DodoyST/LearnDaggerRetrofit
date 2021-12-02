package com.example.daggerbikinpusing.data.repository

import com.example.daggerbikinpusing.data.api.ApiService
import com.example.daggerbikinpusing.data.model.Blog
import retrofit2.Response
import javax.inject.Inject

class BlogRepositoryImpl @Inject constructor(private val apiService: ApiService) : BlogRepository {
  override suspend fun getAllBlog(): Response<List<Blog>> = apiService.getAll()
  override suspend fun getBlogById(id: String): Response<Blog> = apiService.getById(id)
}
