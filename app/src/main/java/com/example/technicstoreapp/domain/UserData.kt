package com.example.technicstoreapp.domain

data class UserData(
    var id: String,
    var name: String,
    var hashPassword: String,
    var number: String,
    var address: String,
    var email: String,
    var discountPoints: String,
    var carts: List<CartTechnicData>,
    val dateOfBirth: String
)