package com.example.technicstoreapp.di.module

import com.example.technicstoreapp.data.network.TechService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class TechModule {

    @Provides
    @Named("retrofitTech")
    @Singleton
    fun getRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://android-api.herokuapp.com/Technic/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun getTechService(@Named("retrofitTech") retrofit: Retrofit): TechService {
        return retrofit.create(TechService::class.java)
    }
}