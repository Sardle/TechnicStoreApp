package com.example.technicstoreapp.data.models

import com.squareup.moshi.Json

data class AuthResponse(
    @Json(name = "result") val result: UserResponse? = null,
    @Json(name = "isSuccess") val isSuccess: Boolean = false
)
