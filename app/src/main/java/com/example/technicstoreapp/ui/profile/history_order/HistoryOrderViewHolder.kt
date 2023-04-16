package com.example.technicstoreapp.ui.profile.history_order

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.technicstoreapp.databinding.HistoryOrderItemBinding
import com.example.technicstoreapp.domain.models.HistoryOrderItem

class HistoryOrderViewHolder(private val binding: HistoryOrderItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: HistoryOrderItem.HistoryTechnic) {
        binding.historyItemName.text = item.name
        binding.historyOrderPrice.text = (item.price * item.count).toString() + RUB
        getPhoto(item.imageUrl, binding.historyOrderImage)
        binding.historyItemCount.text = item.count.toString() + COUNTER
        binding.historyOrderColor.text = item.color
    }

    private fun getPhoto(url: String, image: ImageView) {
        Glide.with(image)
            .load(url)
            .into(image)
    }

    companion object {
        private const val RUB = " р."

        private const val COUNTER = " шт."
    }
}