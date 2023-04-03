package com.example.technicstoreapp.data.network

import com.example.technicstoreapp.data.models.AuthResponse
import com.example.technicstoreapp.data.models.LogInResponse
import com.example.technicstoreapp.data.models.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface UserService {

    @GET("UserTech/get-all-user")
    fun getAll(): Call<List<UserResponse>>

    @GET("UserTech/get-user-by-id")
    fun getUserById(@Query("id") id: String): Call<UserResponse>

    @POST("UserTech/login")
    fun getLogIn(@Body logInResponse: LogInResponse): Call<AuthResponse>

    @POST("UserTech/create-user")
    fun createUser(@Body user: UserResponse): Call<AuthResponse>

    @PUT("UserTech/update-user")
    fun updateUser(@Body user: UserResponse): Call<Boolean>
}