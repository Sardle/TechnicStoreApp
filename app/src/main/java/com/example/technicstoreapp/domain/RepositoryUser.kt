package com.example.technicstoreapp.domain

import com.example.technicstoreapp.domain.models.HistoryOrderData
import com.example.technicstoreapp.domain.models.HistoryOrderItem
import com.example.technicstoreapp.domain.models.TechnicData
import com.example.technicstoreapp.domain.models.UserData

interface RepositoryUser {

    fun checkAvailabilityUser(): Boolean

    suspend fun createUser(userData: UserData): Boolean

    fun setPrefs(id: String)

    fun logOutUser()

    suspend fun deleteUser()

    suspend fun addToFavourite(TechnicData: TechnicData)

    suspend fun removeFromFavourite(technicData: TechnicData)

    suspend fun checkToFavourite(technicData: TechnicData): Boolean?

    suspend fun getFavouriteTechnic() : List<TechnicData>

    suspend fun getHistoryOrderItem() : List<HistoryOrderItem>

    suspend fun getUserById(): UserData

    suspend fun updateUser(historyOrderData: HistoryOrderData, address: String, points: Int)

    suspend fun checkLogInUser(number: String, password: String): Boolean
}