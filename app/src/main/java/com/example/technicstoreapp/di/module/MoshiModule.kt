package com.example.technicstoreapp.di.module

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoshiModule {

    @Provides
    @Singleton
    fun getMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory())
        .build()
}