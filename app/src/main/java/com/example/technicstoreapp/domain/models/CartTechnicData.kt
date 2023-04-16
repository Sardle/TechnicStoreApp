package com.example.technicstoreapp.domain.models

data class CartTechnicData(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val description: String,
    val price: Double,
    val category: String,
    val color: String,
    val count: Int,
)
