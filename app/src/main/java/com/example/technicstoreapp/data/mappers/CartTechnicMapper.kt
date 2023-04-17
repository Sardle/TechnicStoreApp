package com.example.technicstoreapp.data.mappers

import com.example.technicstoreapp.data.models.CartTechnicResponse
import com.example.technicstoreapp.domain.models.CartTechnicData
import javax.inject.Inject

class CartTechnicMapper @Inject constructor() {

    fun dataToResponse(technicData: CartTechnicData): CartTechnicResponse = with(technicData) {
        CartTechnicResponse(
            id = id,
            name = name,
            imageUrl = imageUrl,
            description = description,
            price = price,
            category = category,
            color = category,
            count = count,
        )
    }

    fun responseToData(cartTechnicResponse: CartTechnicResponse): CartTechnicData = with(cartTechnicResponse) {
        CartTechnicData(
            id = id ?: 0,
            name = name.orEmpty(),
            imageUrl = imageUrl.orEmpty(),
            description = description.orEmpty(),
            price = price ?: 0.0,
            category = category.orEmpty(),
            color = category.orEmpty(),
            count = count ?: 0,
        )
    }
}