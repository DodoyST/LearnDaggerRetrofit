package com.example.daggerbikinpusing.data.api

import com.example.daggerbikinpusing.data.model.Blog
import com.example.daggerbikinpusing.data.model.Login
import com.example.daggerbikinpusing.data.model.response.LoginResponse
import com.example.daggerbikinpusing.util.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
  @POST(Constants.LOGIN_URL)
  suspend fun login(@Body login: Login): Response<LoginResponse>
  
  @GET(Constants.BLOG_URL)
  suspend fun getAll(): Response<List<Blog>>
}
