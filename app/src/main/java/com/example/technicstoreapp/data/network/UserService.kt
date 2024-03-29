package com.example.technicstoreapp.data.network

import com.example.technicstoreapp.data.models.AuthResponse
import com.example.technicstoreapp.data.models.LogInResponse
import com.example.technicstoreapp.data.models.UserResponse
import retrofit2.http.*

interface UserService {

    @GET("get-user-by-id")
    suspend fun getUserById(@Query("id") id: String): UserResponse

    @POST("login")
    suspend fun getLogIn(@Body logInResponse: LogInResponse): AuthResponse

    @POST("create-user")
    suspend fun createUser(@Body user: UserResponse): AuthResponse

    @HTTP(method = "DELETE", path = "delete-user", hasBody = true)
    suspend fun deleteUser(@Body user: UserResponse): Boolean

    @PUT("update-user")
    suspend fun updateUser(@Body user: UserResponse): Boolean
}