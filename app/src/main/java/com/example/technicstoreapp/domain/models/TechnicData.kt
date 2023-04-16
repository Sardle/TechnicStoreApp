package com.example.technicstoreapp.domain.models

data class TechnicData(
    val id: Int,
    val name: String,
    val colors: Map<String, String>,
    val description: String,
    val price: Double,
    val category: String
)
