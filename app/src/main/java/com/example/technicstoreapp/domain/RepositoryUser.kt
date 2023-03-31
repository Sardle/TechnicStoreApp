package com.example.technicstoreapp.domain

interface RepositoryUser {

    fun checkAvailabilityUser(): Boolean

    fun addUserToList(userData: UserData)

    fun setPrefsUserId(number: String, password: String)

    fun logOutUser()

    fun getUser(): UserData

    fun getUserById(idUser: String): UserData

    fun checkLogInUser(number: String, password: String): UserData?
}