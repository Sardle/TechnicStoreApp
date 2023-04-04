package com.example.technicstoreapp.data.models

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("surname") var surname: String? = null,
    @SerializedName("age") val age: String? = null,
    @SerializedName("hashPassword") val hashPassword: String? = null,
    @SerializedName("number") var number: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("discountPoints") var discountPoints: String? = null,
    @SerializedName("carts") var carts: List<HistoryOrderResponse>? = null,
    @SerializedName("dateOfBirth") val dateOfBirth: String? = null
)