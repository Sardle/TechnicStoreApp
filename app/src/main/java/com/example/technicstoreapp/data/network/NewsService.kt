package com.example.technicstoreapp.data.network

import com.example.technicstoreapp.data.models.NewsArticlesResponse
import retrofit2.Call
import retrofit2.http.*

interface NewsService {

    @GET("everything")
    fun getNews(@Query("q") q: String, @Header("x-api-key") value: String): Call<NewsArticlesResponse>
}