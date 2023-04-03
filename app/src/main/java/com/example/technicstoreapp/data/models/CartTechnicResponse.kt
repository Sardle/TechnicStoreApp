package com.example.technicstoreapp.data.models

import com.google.gson.annotations.SerializedName

data class CartTechnicResponse (
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("imageUrl") val imageUrl: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("price") val price: Double? = null,
    @SerializedName("category") val category: String? = null,
    @SerializedName("color") val color: String? = null,
    @SerializedName("count") val count: Int? = null,
    @SerializedName("orderTime") val orderTime: String? = null
)