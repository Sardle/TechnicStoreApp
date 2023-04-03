package com.example.technicstoreapp.data

import com.example.technicstoreapp.data.mappers.UserMapper
import com.example.technicstoreapp.data.models.LogInResponse
import com.example.technicstoreapp.data.network.UserService
import com.example.technicstoreapp.data.source.UserDataSource
import com.example.technicstoreapp.domain.RepositoryUser
import com.example.technicstoreapp.domain.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.security.MessageDigest
import javax.inject.Inject

class RepositoryUserImpl @Inject constructor(
    private val prefs: UserDataSource,
    private val service: UserService,
    private val userMapper: UserMapper
) : RepositoryUser {

    override fun checkAvailabilityUser(): Boolean = prefs.getUserId().isEmpty()

    override suspend fun createUser(userData: UserData): Boolean {
        var isSuccess = false
        withContext(Dispatchers.IO) {
            val user = encryptIdAndPassword(userData)
            val authResponse = service.createUser(userMapper.dataToResponse(user)).execute().body()
                ?: throw Exception()
            isSuccess = if (authResponse.isSuccess) {
                setPrefs(user.id)
                true
            } else {
                false
            }
        }
        return isSuccess
    }

    override fun setPrefs(id: String) {
        prefs.setUserId(id)
    }

    override fun logOutUser() {
        prefs.setUserId("")
    }

    override suspend fun getUserById(): UserData {
        return withContext(Dispatchers.IO) {
            userMapper.responseToData(
                service.getUserById(prefs.getUserId()).execute().body() ?: throw Exception()
            )
        }
    }

    override suspend fun updateUser(points: String) {
        withContext(Dispatchers.IO) {
            val user = getUserById()

            user.discountPoints = points
            service.updateUser(userMapper.dataToResponse(user)).execute().body()
        }
    }

    override suspend fun checkLogInUser(number: String, password: String): Boolean {
        var isSuccess = false
        withContext(Dispatchers.IO) {
            val authResponse =
                service.getLogIn(LogInResponse(number, encryptField(password))).execute().body()
                    ?: throw Exception()
            isSuccess = authResponse.isSuccess
            if (authResponse.isSuccess) {
                setPrefs(userMapper.responseToData(authResponse.result).id)
                userMapper.responseToData(authResponse.result)
            } else {
                null
            }
        }
        return isSuccess
    }


    private fun encryptIdAndPassword(userData: UserData): UserData {
        userData.id = encryptField(userData.number)
        userData.hashPassword = encryptField(userData.hashPassword)
        return userData
    }

    private fun encryptField(field: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        md.update(field.toByteArray())
        val digest = md.digest()

        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}