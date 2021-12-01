package com.example.daggerbikinpusing.di.module

import com.example.daggerbikinpusing.data.repository.LoginRepository
import com.example.daggerbikinpusing.data.repository.LoginRepositoryImpl
import com.example.daggerbikinpusing.di.annotation.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class LoginModule {
  
  @Binds
  @ActivityScope
  abstract fun bindsLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository
}
