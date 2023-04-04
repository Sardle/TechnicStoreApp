package com.example.technicstoreapp.di

import com.example.technicstoreapp.data.network.TechService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class TechModule {

    @Provides
    @Named("retrofitTech")
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://android-api.herokuapp.com/Technic/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun getTechService(@Named("retrofitTech") retrofit: Retrofit): TechService {
        return retrofit.create(TechService::class.java)
    }
}