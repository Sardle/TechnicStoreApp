package com.example.technicstoreapp.data.mappers

import com.example.technicstoreapp.data.models.HistoryOrderResponse
import com.example.technicstoreapp.domain.models.HistoryOrderData
import javax.inject.Inject

class HistoryOrderMapper @Inject constructor(
    private val cartTechnicMapper: CartTechnicMapper
) {

    fun dataToResponse(historyOrderData: HistoryOrderData): HistoryOrderResponse =
        with(historyOrderData) {
            HistoryOrderResponse(
                orderTime = orderTime,
                cartTechnicResponse = cartTechnicData.map {
                    cartTechnicMapper.dataToResponse(
                        it
                    )
                },
                totalCount = totalCount
            )
        }

    fun responseToData(historyOrderResponse: HistoryOrderResponse): HistoryOrderData =
        with(historyOrderResponse) {
            HistoryOrderData(
                orderTime = orderTime.orEmpty(),
                cartTechnicData = cartTechnicResponse?.map {
                    cartTechnicMapper.responseToData(
                        it
                    )
                } ?: emptyList(),
                totalCount = totalCount ?: 0.0
            )
        }
}