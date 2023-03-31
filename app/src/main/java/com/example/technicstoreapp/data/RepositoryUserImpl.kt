package com.example.technicstoreapp.data

import com.example.technicstoreapp.data.source.UserDataSource
import com.example.technicstoreapp.domain.RepositoryUser
import com.example.technicstoreapp.domain.UserData
import java.security.MessageDigest
import javax.inject.Inject

class RepositoryUserImpl @Inject constructor(
    private val server: Server,
    private val prefs: UserDataSource,
) : RepositoryUser {

    override fun checkAvailabilityUser(): Boolean = prefs.getUserId().isEmpty()

    override fun addUserToList(userData: UserData) {
        userData.encryptIdAndPassword()
        prefs.setUserId(userData.id)
        server.addUserToList(userData)
    }

    override fun setPrefsUserId(number: String, password: String) {
        server.checkLogIn(encryptField(number), encryptField(password))
            ?.let { prefs.setUserId(it.id) }
    }

    override fun logOutUser() {
        prefs.setUserId("")
    }

    override fun getUser(): UserData = getUserById(prefs.getUserId())

    override fun getUserById(idUser: String): UserData = server.getUserById(idUser)

    override fun checkLogInUser(number: String, password: String): UserData? = server.checkLogIn(encryptField(number), encryptField(password))


    private fun UserData.encryptIdAndPassword() {
        id = encryptField(number)
        password = encryptField(password)
    }

    private fun encryptField(field: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        md.update(field.toByteArray())
        val digest = md.digest()

        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}