package com.example.technicstoreapp.data.mappers

import com.example.technicstoreapp.data.models.CartTechnicResponse
import com.example.technicstoreapp.domain.CartTechnicData
import javax.inject.Inject

class CartTechnicMapper @Inject constructor() {

    operator fun invoke(technicData: CartTechnicData): CartTechnicResponse = with(technicData) {
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
}