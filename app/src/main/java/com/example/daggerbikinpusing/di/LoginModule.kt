package com.example.daggerbikinpusing.di

import com.example.daggerbikinpusing.data.repository.LoginRepository
import com.example.daggerbikinpusing.data.repository.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class LoginModule {
  
  @Binds
  @Singleton
  abstract fun bindsLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository
}
