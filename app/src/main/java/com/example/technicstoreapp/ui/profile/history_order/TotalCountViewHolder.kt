package com.example.technicstoreapp.ui.profile.history_order

import androidx.recyclerview.widget.RecyclerView
import com.example.technicstoreapp.databinding.TextItemTotalCountBinding
import com.example.technicstoreapp.domain.models.HistoryOrderItem

class TotalCountViewHolder(private val binding: TextItemTotalCountBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: HistoryOrderItem.TotalCount) {
        binding.totalCountOrder.text = TOTAL_COUNT + item.totalCount
    }

    companion object {
        private const val TOTAL_COUNT = "Общая сумма заказа: "
    }
}