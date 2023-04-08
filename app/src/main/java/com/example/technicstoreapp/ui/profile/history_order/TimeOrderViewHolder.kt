package com.example.technicstoreapp.ui.profile.history_order

import androidx.recyclerview.widget.RecyclerView
import com.example.technicstoreapp.databinding.TextItemBinding
import com.example.technicstoreapp.domain.HistoryOrderItem

class TimeOrderViewHolder(private val binding: TextItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: HistoryOrderItem.TimeOrder) {
        binding.timeOrder.text = item.timeOrder
    }
}