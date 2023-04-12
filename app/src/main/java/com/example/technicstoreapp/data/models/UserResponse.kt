package com.example.technicstoreapp.data.models

import com.squareup.moshi.Json

data class UserResponse(
    @Json(name = "id") val id: String? = null,
    @Json(name = "name") var name: String? = null,
    @Json(name = "surname") var surname: String? = null,
    @Json(name = "age") val age: String? = null,
    @Json(name = "hashPassword") val hashPassword: String? = null,
    @Json(name = "number") var number: String? = null,
    @Json(name = "email") val email: String? = null,
    @Json(name = "address") var address: String? = null,
    @Json(name = "discountPoints") var discountPoints: Int? = null,
    @Json(name = "carts") var carts: List<HistoryOrderResponse>? = null,
    @Json(name = "dateOfBirth") val dateOfBirth: String? = null,
    @Json(name = "favouriteTechnicResponse") val favouriteTechnicResponse: List<TechnicResponse>? = null
)