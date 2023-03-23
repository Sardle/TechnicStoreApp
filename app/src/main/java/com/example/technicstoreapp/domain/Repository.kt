package com.example.technicstoreapp.domain

interface Repository {

    fun getAllTechnic(): List<TechnicData>

    suspend fun getNews(): List<NewsData>

    fun setUserToken(token: String)

    fun getCategories(): List<String>

    fun getTechnicBasedFromCategory(category: String): List<TechnicData>

    suspend fun insertTechnic(technicData: TechnicData)

    suspend fun getAllTechnicFromCart(): List<CartTechnicData>

    suspend fun deleteAllTechnicFromCart(listTechnic: List<CartTechnicData>)

    fun getTechnicInfo(id: Int): TechnicData

    suspend fun removeUnitTechnic(technicData: TechnicData)

    suspend fun deleteTechnic(technicData: TechnicData)

    suspend fun getSumCurrentPrices(): Double

    fun getSearchResult(searchString: String): List<TechnicData>
}