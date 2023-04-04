package com.example.technicstoreapp.domain

data class TechnicData(
    val id: Int,
    val name: String,
    val colors: Map<String, String>,
    val description: String,
    val price: Double,
    val category: String
)
