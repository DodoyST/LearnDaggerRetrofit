package com.example.daggerbikinpusing.util

import android.app.Application
import com.example.daggerbikinpusing.di.AppComponent
import com.example.daggerbikinpusing.di.AppModule
import com.example.daggerbikinpusing.di.DaggerAppComponent

class BaseApplication : Application() {
  fun appComponent(): AppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
}
