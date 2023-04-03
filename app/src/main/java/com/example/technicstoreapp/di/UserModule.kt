package com.example.technicstoreapp.di

import com.example.technicstoreapp.data.network.NewsService
import com.example.technicstoreapp.data.network.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
            .baseUrl("https://android-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun getNewsService(@Named("retrofitUser") retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }
}