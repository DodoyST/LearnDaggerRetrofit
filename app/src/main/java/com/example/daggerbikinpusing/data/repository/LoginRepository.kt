package com.example.daggerbikinpusing.data.repository

import com.example.daggerbikinpusing.data.model.Login
import com.example.daggerbikinpusing.data.model.response.LoginResponse
import retrofit2.Response

interface LoginRepository {
  suspend fun login(login: Login): Response<LoginResponse>
}
