package com.example.daggerbikinpusing.util

import android.app.Application
import com.example.daggerbikinpusing.di.component.AppComponent
import com.example.daggerbikinpusing.di.component.CoreComponent
import com.example.daggerbikinpusing.di.component.DaggerAppComponent
import com.example.daggerbikinpusing.di.component.DaggerCoreComponent
import com.example.daggerbikinpusing.di.module.DataModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class BaseApplication : Application(), HasAndroidInjector {
  
  @Inject
  lateinit var dispatchAndroidInjector: DispatchingAndroidInjector<Any>
  
  private fun provideCoreComponent() = coreComponent()
  
  private fun coreComponent(): CoreComponent =
    DaggerCoreComponent.builder().dataModule(DataModule(this)).build()
  
  private fun appComponent(): AppComponent =
    DaggerAppComponent.builder().coreComponent(provideCoreComponent()).build()
  
  override fun onCreate() {
    super.onCreate()
    appComponent().inject(this)
  }
  
  override fun androidInjector(): AndroidInjector<Any> = dispatchAndroidInjector
}
