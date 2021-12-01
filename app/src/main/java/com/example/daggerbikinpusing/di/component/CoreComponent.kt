package com.example.daggerbikinpusing.di.component

import android.content.SharedPreferences
import com.example.daggerbikinpusing.data.api.ApiService
import com.example.daggerbikinpusing.di.module.ApiModule
import com.example.daggerbikinpusing.di.module.DataModule
import com.example.daggerbikinpusing.util.SessionManager
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApiModule::class, DataModule::class])
@Singleton
interface CoreComponent {
  fun provideSharedPreferences(): SharedPreferences
  fun provideSessionManager(): SessionManager
  fun provideApiService(): ApiService
}
