package com.example.technicstoreapp.domain.models

import com.example.technicstoreapp.domain.models.CartTechnicData

data class HistoryOrderData(
    val orderTime: String,
    val cartTechnicData: List<CartTechnicData>,
    val totalCount: Double
)