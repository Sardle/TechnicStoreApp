package com.example.technicstoreapp.data.models

data class HistoryOrderResponse (
    val orderTime:String? = null,
    val listCartTechnicResponse: List<CartTechnicResponse>? = null
)