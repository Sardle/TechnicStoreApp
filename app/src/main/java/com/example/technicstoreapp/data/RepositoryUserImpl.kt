package com.example.technicstoreapp.data

import com.example.technicstoreapp.data.mappers.HistoryOrderItemMapper
import com.example.technicstoreapp.data.mappers.UserMapper
import com.example.technicstoreapp.data.models.LogInResponse
import com.example.technicstoreapp.data.network.UserService
import com.example.technicstoreapp.data.source.UserDataSource
import com.example.technicstoreapp.domain.*
import com.example.technicstoreapp.domain.models.HistoryOrderData
import com.example.technicstoreapp.domain.models.HistoryOrderItem
import com.example.technicstoreapp.domain.models.TechnicData
import com.example.technicstoreapp.domain.models.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.security.MessageDigest
import javax.inject.Inject

class RepositoryUserImpl @Inject constructor(
    private val prefs: UserDataSource,
    private val service: UserService,
    private val userMapper: UserMapper,
    private val historyOrderItemMapper: HistoryOrderItemMapper
) : RepositoryUser {

    override fun checkAvailabilityUser(): Boolean = prefs.getUserId().isEmpty()

    override suspend fun createUser(userData: UserData): Boolean {
        var isSuccess: Boolean
        withContext(Dispatchers.IO) {
            val user = encryptIdAndPassword(userData)
            val authResponse = service.createUser(userMapper.dataToResponse(user))
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

    override suspend fun deleteUser() {
        withContext(Dispatchers.IO) {
            val user = getUserById()
            service.deleteUser(userMapper.dataToResponse(user))
            logOutUser()
        }
    }

    override suspend fun addToFavourite(TechnicData: TechnicData) {
        withContext(Dispatchers.IO) {
            val user = getUserById()

            val listFavourite = user.favouriteTechnicData.toMutableList()
            listFavourite.add(TechnicData)
            user.favouriteTechnicData = listFavourite.toList()
            service.updateUser(userMapper.dataToResponse(user))
        }
    }

    override suspend fun removeFromFavourite(technicData: TechnicData) {
        withContext(Dispatchers.IO) {
            val user = getUserById()

            val listFavourite = user.favouriteTechnicData.toMutableList()
            listFavourite.remove(technicData)
            user.favouriteTechnicData = listFavourite.toList()
            service.updateUser(userMapper.dataToResponse(user))
        }
    }

    override suspend fun checkToFavourite(technicData: TechnicData): Boolean? {
        return withContext(Dispatchers.IO) {
            try {
                getUserById().favouriteTechnicData.contains(technicData)
            } catch (e: Exception) {
                null
            }
        }
    }

    override suspend fun getFavouriteTechnic(): List<TechnicData> {
        return withContext(Dispatchers.IO) {
            getUserById().favouriteTechnicData
        }
    }

    override suspend fun getHistoryOrderItem(): List<HistoryOrderItem> {
        return withContext(Dispatchers.IO) {
            val user = getUserById()
            historyOrderItemMapper(user.carts)
        }
    }

    override suspend fun getUserById(): UserData {
        return withContext(Dispatchers.IO) {
            userMapper.responseToData(
                service.getUserById(prefs.getUserId())
            )
        }
    }

    override suspend fun updateUser(
        historyOrderData: HistoryOrderData,
        address: String,
        points: Int
    ) {
        withContext(Dispatchers.IO) {
            val user = getUserById()

            user.address = address
            val listHistory = user.carts.toMutableList()
            listHistory.add(historyOrderData)
            user.carts = listHistory.toList()
            user.discountPoints += points
            service.updateUser(userMapper.dataToResponse(user))
        }
    }

    override suspend fun checkLogInUser(number: String, password: String): Boolean {
        var isSuccess: Boolean
        withContext(Dispatchers.IO) {
            val authResponse =
                service.getLogIn(LogInResponse(number, encryptField(password)))
            isSuccess = authResponse.isSuccess
            if (authResponse.isSuccess) {
                authResponse.result?.let { userMapper.responseToData(it).id }?.let { setPrefs(it) }
                authResponse.result?.let { userMapper.responseToData(it) }
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