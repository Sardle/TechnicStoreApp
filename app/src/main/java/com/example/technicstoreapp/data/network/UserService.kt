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

    @GET("get-all-user")
    fun getAll(): Call<List<UserResponse>>

    @GET("get-user-by-id")
    fun getUserById(@Query("id") id: String): Call<UserResponse>

    @POST("login")
    fun getLogIn(@Body logInResponse: LogInResponse): Call<AuthResponse>

    @POST("create-user")
    fun createUser(@Body user: UserResponse): Call<AuthResponse>

    @PUT("update-user")
    fun updateUser(@Body user: UserResponse): Call<Boolean>
}