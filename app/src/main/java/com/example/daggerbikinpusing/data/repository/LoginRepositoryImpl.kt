package com.example.daggerbikinpusing.data.repository

import com.example.daggerbikinpusing.data.api.ApiService
import com.example.daggerbikinpusing.data.model.Login
import com.example.daggerbikinpusing.data.model.response.LoginResponse
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val apiService: ApiService) :
  LoginRepository {
  override suspend fun login(login: Login): Response<LoginResponse> = apiService.login(login)
}
