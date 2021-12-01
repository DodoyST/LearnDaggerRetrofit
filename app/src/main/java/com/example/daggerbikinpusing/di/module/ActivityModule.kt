package com.example.daggerbikinpusing.di.module

import com.example.daggerbikinpusing.ui.activity.MainActivity
import com.example.daggerbikinpusing.ui.screen.HomeFragment
import com.example.daggerbikinpusing.ui.screen.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
  
  @ContributesAndroidInjector
  abstract fun contributeMainActivity(): MainActivity
  
  @ContributesAndroidInjector
  abstract fun contributeLoginFragment(): LoginFragment
  
  @ContributesAndroidInjector
  abstract fun contributeHomeFragment(): HomeFragment
}
