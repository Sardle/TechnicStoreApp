package com.example.technicstoreapp.domain

interface Repository {

    fun getAllTechnic(): List<TechnicData>

    suspend fun getNews(): List<NewsData>

    fun setUserToken(token: String)

    fun getCategories(): List<String>

    fun getTechnicBasedFromCategory(category: String): List<TechnicData>

    suspend fun plusUnitTechnic(id: Int, color: String)

    suspend fun insertTechnic(technicData: TechnicData, color: String)

    suspend fun getAllTechnicFromCart(): List<CartTechnicData>

    suspend fun deleteAllTechnicFromCart(listTechnic: List<CartTechnicData>)

    fun getTechnicInfo(id: Int): TechnicData

    suspend fun removeUnitTechnic(id: Int, color: String)

    suspend fun deleteTechnic(id: Int, color: String)

    suspend fun getSumCurrentPrices(): Double

    fun getSearchResult(searchString: String): List<TechnicData>
}