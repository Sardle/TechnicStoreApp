package com.example.technicstoreapp.domain

import com.example.technicstoreapp.domain.models.CartTechnicData
import com.example.technicstoreapp.domain.models.NewsData
import com.example.technicstoreapp.domain.models.TechnicData

interface RepositoryTech {

    suspend fun getAllTechnic(): List<TechnicData>

    suspend fun getNews(): List<NewsData>

    fun setUserToken(token: String)

    suspend fun getCategories(): List<String>

    suspend fun getTechnicBasedFromCategory(category: String): List<TechnicData>

    suspend fun plusUnitTechnic(id: Int, color: String)

    suspend fun insertTechnic(technicData: TechnicData, color: String)

    suspend fun getAllTechnicFromCart(): List<CartTechnicData>

    suspend fun deleteAllTechnicFromCart()

    suspend fun getTechnicInfo(id: Int): TechnicData

    suspend fun removeUnitTechnic(id: Int, color: String)

    suspend fun deleteTechnic(id: Int, color: String)

    suspend fun getSumCurrentPrices(): Double

    suspend fun getSearchResult(searchString: String): List<TechnicData>

    suspend fun checkListCart(): Boolean

    suspend fun checkIfElementExists(name: String, color: String): Boolean
}