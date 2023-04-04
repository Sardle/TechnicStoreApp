package com.example.technicstoreapp.data.network

import com.example.technicstoreapp.data.models.TechnicResponse
import com.example.technicstoreapp.data.models.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TechService {

    @GET("get-technic")
    fun getAllTechnic(): Call<List<TechnicResponse>>

    @GET("get-tech-by-category")
    fun getTechnicByCategory(@Query("category") category: String): Call<List<TechnicResponse>>

    @GET("get-tech-by-id")
    fun getTechnicById(@Query("id") id: Int): Call<TechnicResponse>

    @GET("get-categories")
    fun getCategories(): Call<List<String>>

    @GET("search")
    fun search(@Query("search") search: String): Call<List<TechnicResponse>>
}