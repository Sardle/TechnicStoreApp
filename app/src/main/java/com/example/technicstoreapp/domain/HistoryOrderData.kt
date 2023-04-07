package com.example.technicstoreapp.domain

data class HistoryOrderData(
    val orderTime: String,
    val cartTechnicData: List<CartTechnicData>
)