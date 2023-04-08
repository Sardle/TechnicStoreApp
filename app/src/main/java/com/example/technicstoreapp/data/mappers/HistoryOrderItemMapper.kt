package com.example.technicstoreapp.data.mappers

import com.example.technicstoreapp.domain.CartTechnicData
import com.example.technicstoreapp.domain.HistoryOrderData
import com.example.technicstoreapp.domain.HistoryOrderItem
import javax.inject.Inject

class HistoryOrderItemMapper @Inject constructor(){

    operator fun invoke(historyOrderData: List<HistoryOrderData>): List<HistoryOrderItem> {
        return historyOrderData.flatMap { historyOrder ->
            val listTechnic = historyOrder.cartTechnicData.map { mapCartTechnicToHistoryTechnic(it) }
            listOf(HistoryOrderItem.TimeOrder(historyOrder.orderTime)) + listTechnic
        }
    }

    private fun mapCartTechnicToHistoryTechnic(cartTechnicData: CartTechnicData): HistoryOrderItem.HistoryTechnic = with(cartTechnicData) {
        HistoryOrderItem.HistoryTechnic(
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