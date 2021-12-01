package com.example.daggerbikinpusing.di.module

import com.example.daggerbikinpusing.data.repository.BlogRepository
import com.example.daggerbikinpusing.data.repository.BlogRepositoryImpl
import com.example.daggerbikinpusing.di.annotation.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class BlogModule {
  
  @Binds
  @ActivityScope
  abstract fun bindsBlogRepository(blogRepositoryImpl: BlogRepositoryImpl): BlogRepository
}
