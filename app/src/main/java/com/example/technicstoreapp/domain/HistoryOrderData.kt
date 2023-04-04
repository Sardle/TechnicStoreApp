package com.example.technicstoreapp.domain

data class HistoryOrderData(
    val orderTime: String,
    val listCartTechnicResponse: List<CartTechnicData>
)