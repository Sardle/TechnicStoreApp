package com.example.technicstoreapp.domain

interface Repository {

    fun getAllTechnic(): List<TechnicData>

    suspend fun getNews(): List<NewsData>

    fun setUserToken(token: String)

    fun getCategories(): List<String>

    fun getTechnicBasedFromCategory(category: String): List<TechnicData>
}