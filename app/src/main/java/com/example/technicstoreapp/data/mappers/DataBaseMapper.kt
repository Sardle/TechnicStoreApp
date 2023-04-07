package com.example.technicstoreapp.data.mappers

import com.example.technicstoreapp.data.database.TechnicEntity
import com.example.technicstoreapp.domain.CartTechnicData
import com.example.technicstoreapp.domain.TechnicData
import javax.inject.Inject

class DataBaseMapper @Inject constructor() {

    fun dataToEntity(
        data: TechnicData,
        color: String,
        count: Int,
        currentPrice: Double,
        imageUrl: String
    ): TechnicEntity = with(data) {
        TechnicEntity(
            id = id,
            name = name,
            imageUrl = imageUrl,
            description = description,
            price = price,
            category = category,
            color = color,
            count = count,
            currentPrice = currentPrice
        )
    }

    fun entityToData(data: TechnicEntity): CartTechnicData = with(data) {
        CartTechnicData(
            id = id,
            name = name,
            imageUrl = imageUrl,
            description = description,
            price = price,
            category = category,
            color = color,
            count = count
        )
    }
}
