package com.example.technicstoreapp.data.models

import com.squareup.moshi.Json

data class TechnicResponse(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "colors") val colors: Map<String, String>? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "price") val price: Double? = null,
    @Json(name = "category") val category: String? = null
)
