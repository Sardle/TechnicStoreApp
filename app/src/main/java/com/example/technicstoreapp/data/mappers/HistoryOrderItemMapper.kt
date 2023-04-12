package com.example.technicstoreapp.data.mappers

import com.example.technicstoreapp.domain.CartTechnicData
import com.example.technicstoreapp.domain.HistoryOrderData
import com.example.technicstoreapp.domain.HistoryOrderItem
import javax.inject.Inject

class HistoryOrderItemMapper @Inject constructor() {

    operator fun invoke(historyOrderData: List<HistoryOrderData>): List<HistoryOrderItem> {
        return historyOrderData.flatMap { i ->
            listOf(
                HistoryOrderItem.TimeOrder(i.orderTime),
                *i.cartTechnicData.map { mapCartTechnicToHistoryTechnic(it) }.toTypedArray(),
                HistoryOrderItem.TotalCount(i.totalCount)
            )
        }
    }

    private fun mapCartTechnicToHistoryTechnic(cartTechnicData: CartTechnicData): HistoryOrderItem.HistoryTechnic =
        with(cartTechnicData) {
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