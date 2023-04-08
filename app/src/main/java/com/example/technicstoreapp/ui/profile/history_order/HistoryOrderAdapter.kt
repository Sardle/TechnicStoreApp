package com.example.technicstoreapp.ui.profile.history_order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.technicstoreapp.databinding.HistoryOrderItemBinding
import com.example.technicstoreapp.databinding.TextItemBinding
import com.example.technicstoreapp.domain.HistoryOrderItem

class HistoryOrderAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listHistoryOrderItem = mutableListOf<HistoryOrderItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TECHNIC_TYPE -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val item = HistoryOrderItemBinding.inflate(layoutInflater, parent, false)
                HistoryOrderViewHolder(item)
            }
            TITLE_TYPE -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val item = TextItemBinding.inflate(layoutInflater, parent, false)
                TimeOrderViewHolder(item)
            }
            else -> throw Exception("Invalid Type!")
        }

    override fun getItemCount(): Int = listHistoryOrderItem.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HistoryOrderViewHolder -> holder.onBind(listHistoryOrderItem[position] as HistoryOrderItem.HistoryTechnic)
            is TimeOrderViewHolder -> holder.onBind(listHistoryOrderItem[position] as HistoryOrderItem.TimeOrder)
        }
    }

    override fun getItemViewType(position: Int): Int = when (listHistoryOrderItem[position]) {
        is HistoryOrderItem.HistoryTechnic -> TECHNIC_TYPE
        is HistoryOrderItem.TimeOrder -> TITLE_TYPE
    }

    fun setItems(items: List<HistoryOrderItem>) {
        listHistoryOrderItem.clear()
        listHistoryOrderItem.addAll(items)
        notifyDataSetChanged()
    }

    companion object {
        private const val TITLE_TYPE = 1
        private const val TECHNIC_TYPE = 2
    }
}