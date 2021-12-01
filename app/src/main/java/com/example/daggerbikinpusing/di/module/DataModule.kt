package com.example.daggerbikinpusing.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.daggerbikinpusing.R
import com.example.daggerbikinpusing.util.SessionManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule(private val application: Application) {
  
  @Provides
  @Singleton
  fun provideSharedPreference(): SharedPreferences =
    application.getSharedPreferences(R.string.app_name.toString(), Context.MODE_PRIVATE)
  
  @Provides
  @Singleton
  fun provideSessionManager(sharedPreferences: SharedPreferences): SessionManager =
    SessionManager(sharedPreferences)
}
