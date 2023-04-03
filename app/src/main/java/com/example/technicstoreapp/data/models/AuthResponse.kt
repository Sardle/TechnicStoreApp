package com.example.technicstoreapp.data.models

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("result") val result: UserResponse,
    @SerializedName("isSuccess") val isSuccess: Boolean = false
)
