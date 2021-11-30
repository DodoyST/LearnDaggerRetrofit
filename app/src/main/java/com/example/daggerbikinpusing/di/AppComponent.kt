package com.example.daggerbikinpusing.di

import com.example.daggerbikinpusing.ui.activity.MainActivity
import com.example.daggerbikinpusing.ui.screen.LoginFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, LoginModule::class])
@Singleton
interface AppComponent {
  fun inject(mainActivity: MainActivity)
  fun inject(loginFragment: LoginFragment)
}
