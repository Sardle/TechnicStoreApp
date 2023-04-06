package com.example.technicstoreapp.data.models

import com.squareup.moshi.Json

data class HistoryOrderResponse (
    @Json(name = "orderTime") val orderTime:String? = null,
    @Json(name = "listCartTechnicResponse") val listCartTechnicResponse: List<CartTechnicResponse>? = null
)