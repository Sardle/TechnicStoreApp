package com.example.technicstoreapp.data.mappers

import com.example.technicstoreapp.data.models.TechnicResponse
import com.example.technicstoreapp.domain.TechnicData
import javax.inject.Inject

class TechnicMapper @Inject constructor(){

    operator fun invoke(response: TechnicResponse): TechnicData = with(response) {
        TechnicData(
            name = name.orEmpty(),
            imageUrl = imageUrl.orEmpty(),
            description = description.orEmpty(),
            price = price ?: 0.0
        )
    }
}