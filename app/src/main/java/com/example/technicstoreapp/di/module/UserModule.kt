package com.example.technicstoreapp.di.module

import com.example.technicstoreapp.data.network.UserService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class UserModule {

    @Provides
    @Named("retrofitUser")
    @Singleton
    fun getRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://android-api.herokuapp.com/UserTech/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun getUserService(@Named("retrofitUser") retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }
}