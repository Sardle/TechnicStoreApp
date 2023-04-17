package com.example.technicstoreapp.data.models

import com.squareup.moshi.Json

data class LogInResponse(
    @Json(name = "number") val number: String? = null,
    @Json(name = "hashPassword") val hashPassword: String? = null
)
