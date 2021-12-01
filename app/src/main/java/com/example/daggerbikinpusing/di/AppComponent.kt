package com.example.daggerbikinpusing.di

import com.example.daggerbikinpusing.util.BaseApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(modules = [AppModule::class, LoginModule::class, ActivityModule::class, AndroidInjectionModule::class])
@Singleton
interface AppComponent {
  fun inject(application: BaseApplication)
}
