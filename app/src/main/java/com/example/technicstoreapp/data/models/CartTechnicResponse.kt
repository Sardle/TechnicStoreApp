package com.example.technicstoreapp.data.models

import com.squareup.moshi.Json

data class CartTechnicResponse(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "imageUrl") val imageUrl: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "price") val price: Double? = null,
    @Json(name = "category") val category: String? = null,
    @Json(name = "color") val color: String? = null,
    @Json(name = "count") val count: Int? = null,
)