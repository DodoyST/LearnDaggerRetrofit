package com.example.daggerbikinpusing.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.daggerbikinpusing.R
import com.example.daggerbikinpusing.data.api.ApiService
import com.example.daggerbikinpusing.data.api.AuthInterceptors
import com.example.daggerbikinpusing.util.Constants
import com.example.daggerbikinpusing.util.SessionManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {
  
  @Provides
  @Singleton
  fun provideSharedPreference(): SharedPreferences =
    application.getSharedPreferences(R.string.app_name.toString(), Context.MODE_PRIVATE)
  
  @Provides
  @Singleton
  fun provideSessionManager(sharedPreferences: SharedPreferences): SessionManager =
    SessionManager(sharedPreferences)
  
  @Provides
  @Singleton
  fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
  
  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(Constants.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
  
  @Provides
  @Singleton
  fun provideOkHttpClient(authInterceptors: AuthInterceptors): OkHttpClient =
    OkHttpClient.Builder().addInterceptor(authInterceptors).build()
}
