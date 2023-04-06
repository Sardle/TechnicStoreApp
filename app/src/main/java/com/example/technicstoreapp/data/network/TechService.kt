package com.example.technicstoreapp.data.network

import com.example.technicstoreapp.data.models.TechnicResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TechService {

    @GET("get-technic")
    suspend fun getAllTechnic(): List<TechnicResponse>

    @GET("get-tech-by-category")
    suspend fun getTechnicByCategory(@Query("category") category: String): List<TechnicResponse>

    @GET("get-tech-by-id")
    suspend fun getTechnicById(@Query("id") id: Int): TechnicResponse

    @GET("get-categories")
    suspend fun getCategories(): List<String>

    @GET("search")
    suspend fun search(@Query("search") search: String): List<TechnicResponse>
}