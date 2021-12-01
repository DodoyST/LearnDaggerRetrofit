package com.example.daggerbikinpusing.di.component

import com.example.daggerbikinpusing.di.annotation.ActivityScope
import com.example.daggerbikinpusing.di.module.ActivityModule
import com.example.daggerbikinpusing.di.module.AppModule
import com.example.daggerbikinpusing.di.module.BlogModule
import com.example.daggerbikinpusing.di.module.LoginModule
import com.example.daggerbikinpusing.util.BaseApplication
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(
  modules = [AppModule::class, LoginModule::class, BlogModule::class, ActivityModule::class, AndroidInjectionModule::class],
  dependencies = [CoreComponent::class]
)
@ActivityScope
interface AppComponent {
  fun inject(application: BaseApplication)
}
