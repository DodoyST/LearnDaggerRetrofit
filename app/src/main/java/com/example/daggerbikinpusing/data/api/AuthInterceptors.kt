package com.example.daggerbikinpusing.data.api

import com.example.daggerbikinpusing.util.Constants
import com.example.daggerbikinpusing.util.SessionManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptors @Inject constructor(private val sessionManager: SessionManager) :
  Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    if (!originalRequest.url.toString().contains(Constants.LOGIN_URL)) {
      val requestBuilder = originalRequest.newBuilder()
      requestBuilder.addHeader(
        "Authorization",
        "Bearer ${sessionManager.fetchAuthToken()}"
      )
      return chain.proceed(requestBuilder.build())
    }
    return chain.proceed(originalRequest)
  }
}
