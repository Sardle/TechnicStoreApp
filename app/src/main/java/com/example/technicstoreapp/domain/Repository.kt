package com.example.technicstoreapp.domain

interface Repository {

    fun getAllTechnic(): List<TechnicData>

    suspend fun getNews(): List<NewsData>

    fun setUserToken(token: String)
}