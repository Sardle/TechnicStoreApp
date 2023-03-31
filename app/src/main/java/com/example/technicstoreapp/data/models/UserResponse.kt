package com.example.technicstoreapp.data.models

import com.example.technicstoreapp.domain.CartTechnicData

data class UserResponse(
    val id: String,
    var name: String? = null,
    var surname: String? = null,
    val age: String? = null,
    val password: String? = null,
    var number: String? = null,
    var address: String? = null,
    var discountPoints: String? = null,
    var carts: List<CartTechnicData>? = null
)