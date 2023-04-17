package com.example.technicstoreapp.data.models

import com.squareup.moshi.Json

data class NewsArticlesResponse(
    @Json(name = "articles") val articles: List<NewsResponse>? = null
)
