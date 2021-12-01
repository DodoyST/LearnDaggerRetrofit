package com.example.daggerbikinpusing.util

import android.app.Application
import com.example.daggerbikinpusing.di.AppComponent
import com.example.daggerbikinpusing.di.AppModule
import com.example.daggerbikinpusing.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class BaseApplication : Application(), HasAndroidInjector {
  
  @Inject
  lateinit var dispatchAndroidInjector: DispatchingAndroidInjector<Any>
  
  fun appComponent(): AppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
  
  override fun onCreate() {
    super.onCreate()
    appComponent().inject(this)
  }
  
  override fun androidInjector(): AndroidInjector<Any> = dispatchAndroidInjector
}
