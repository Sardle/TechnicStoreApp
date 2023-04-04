package com.example.technicstoreapp.data.mappers

import com.example.technicstoreapp.data.models.UserResponse
import com.example.technicstoreapp.domain.CartTechnicData
import com.example.technicstoreapp.domain.HistoryOrderData
import com.example.technicstoreapp.domain.UserData
import javax.inject.Inject

class UserMapper @Inject constructor(
    private val historyOrderMapper: HistoryOrderMapper
) {

    fun responseToData(userResponse: UserResponse): UserData = with(userResponse) {
        UserData(
            id = id.orEmpty(),
            name = name.orEmpty(),
            hashPassword = hashPassword.orEmpty(),
            number = number.orEmpty(),
            address = address.orEmpty(),
            email = email.orEmpty(),
            discountPoints = discountPoints.orEmpty(),
            carts = ((carts ?: emptyList()) as List<HistoryOrderData>),
            dateOfBirth = dateOfBirth.orEmpty()
        )
    }

    fun dataToResponse(userData: UserData): UserResponse = with(userData) {
        UserResponse(
            id = id,
            name = name,
            hashPassword = hashPassword,
            number = number,
            address = address,
            email = email,
            discountPoints = discountPoints,
            carts = carts.map { historyOrderMapper(it) },
            dateOfBirth = dateOfBirth
        )
    }
}