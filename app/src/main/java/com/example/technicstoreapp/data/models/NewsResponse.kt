package com.example.technicstoreapp.data.models

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("author") val author: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("urlToImage") val urlToImage: String? = null,
)
