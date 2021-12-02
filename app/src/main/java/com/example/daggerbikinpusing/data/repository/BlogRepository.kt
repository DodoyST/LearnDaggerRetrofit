package com.example.daggerbikinpusing.data.repository

import com.example.daggerbikinpusing.data.model.Blog
import retrofit2.Response

interface BlogRepository {
  suspend fun getAllBlog(): Response<List<Blog>>
  suspend fun getBlogById(id: String): Response<Blog>
}
