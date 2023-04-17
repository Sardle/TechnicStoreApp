package com.example.technicstoreapp.domain.models

data class HistoryOrderData(
    val orderTime: String,
    val cartTechnicData: List<CartTechnicData>,
    val totalCount: Double
)