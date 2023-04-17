package com.example.technicstoreapp.data.models

import com.squareup.moshi.Json

data class HistoryOrderResponse(
    @Json(name = "orderTime") val orderTime: String? = null,
    @Json(name = "cartTechnicResponse") val cartTechnicResponse: List<CartTechnicResponse>? = null,
    @Json(name = "totalCount") val totalCount: Double? = null
)