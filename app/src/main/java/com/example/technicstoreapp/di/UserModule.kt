package com.example.technicstoreapp.di

import com.example.technicstoreapp.data.network.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class UserModule {

    @Provides
    @Named("retrofitUser")
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://android-api.herokuapp.com/UserTech/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun getUserService(@Named("retrofitUser") retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }
}