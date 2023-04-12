package com.example.technicstoreapp.domain

sealed class HistoryOrderItem {
    data class HistoryTechnic(
        val id: Int,
        val name: String,
        val imageUrl: String,
        val description: String,
        val price: Double,
        val category: String,
        val color: String,
        val count: Int,
    ) : HistoryOrderItem()

    data class TimeOrder(
        val timeOrder: String
    ) : HistoryOrderItem()

    data class TotalCount(
        val totalCount: Double
    ) : HistoryOrderItem()
}
