package com.example.technicstoreapp.data.models

data class TechnicResponse(
    val id: Int? = null,
    val name: String? = null,
    val colors: Map<String, String>? = null,
    val description: String? = null,
    val price: Double? = null,
    val category: String? = null
)
