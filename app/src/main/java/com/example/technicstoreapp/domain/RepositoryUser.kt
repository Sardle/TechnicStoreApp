package com.example.technicstoreapp.domain

interface RepositoryUser {

    fun checkAvailabilityUser(): Boolean

    suspend fun createUser(userData: UserData): Boolean

    fun setPrefs(id: String)

    fun logOutUser()

    suspend fun getUserById(): UserData

    suspend fun updateUser(points: String)

    suspend fun checkLogInUser(number: String, password: String): Boolean
}