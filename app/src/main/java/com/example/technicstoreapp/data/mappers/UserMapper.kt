package com.example.technicstoreapp.data.mappers

import com.example.technicstoreapp.data.models.UserResponse
import com.example.technicstoreapp.domain.models.UserData
import javax.inject.Inject

class UserMapper @Inject constructor(
    private val historyOrderMapper: HistoryOrderMapper,
    private val technicMapper: TechnicMapper
) {

    fun responseToData(userResponse: UserResponse): UserData = with(userResponse) {
        UserData(
            id = id.orEmpty(),
            name = name.orEmpty(),
            hashPassword = hashPassword.orEmpty(),
            number = number.orEmpty(),
            address = address.orEmpty(),
            email = email.orEmpty(),
            discountPoints = discountPoints ?: 0,
            carts = carts?.map { historyOrderMapper.responseToData(it) } ?: emptyList(),
            dateOfBirth = dateOfBirth.orEmpty(),
            favouriteTechnicData = favouriteTechnicResponse?.map { technicMapper(it) } ?: emptyList()
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
            carts = carts.map { historyOrderMapper.dataToResponse(it) },
            dateOfBirth = dateOfBirth,
            favouriteTechnicResponse = favouriteTechnicData.map { technicMapper(it) }
        )
    }
}