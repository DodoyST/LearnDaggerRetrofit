package com.example.daggerbikinpusing.di.module

import androidx.lifecycle.ViewModel
import com.example.daggerbikinpusing.data.viewmodel.LoginViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule() {
  
  @Binds
  abstract fun bindsLoginViewModel(loginViewModel: LoginViewModel): ViewModel
}
