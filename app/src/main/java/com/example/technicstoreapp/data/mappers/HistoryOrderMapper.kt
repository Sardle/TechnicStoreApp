package com.example.technicstoreapp.data.mappers

import com.example.technicstoreapp.data.models.CartTechnicResponse
import com.example.technicstoreapp.data.models.HistoryOrderResponse
import com.example.technicstoreapp.domain.CartTechnicData
import com.example.technicstoreapp.domain.HistoryOrderData
import javax.inject.Inject

class HistoryOrderMapper @Inject constructor(
    private val cartTechnicMapper: CartTechnicMapper
) {

    operator fun invoke(historyOrderData: HistoryOrderData): HistoryOrderResponse = with(historyOrderData) {
        HistoryOrderResponse(
            orderTime = orderTime,
            listCartTechnicResponse = listCartTechnicResponse.map { cartTechnicMapper(it) }
        )
    }
}