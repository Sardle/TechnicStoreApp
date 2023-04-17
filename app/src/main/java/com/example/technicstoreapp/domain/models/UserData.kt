package com.example.technicstoreapp.domain.models

data class UserData(
    val id: String,
    val name: String,
    val hashPassword: String,
    val number: String,
    val address: String,
    val email: String,
    val discountPoints: Int,
    val carts: List<HistoryOrderData>,
    val dateOfBirth: String,
    val favouriteTechnicData: List<TechnicData>
)