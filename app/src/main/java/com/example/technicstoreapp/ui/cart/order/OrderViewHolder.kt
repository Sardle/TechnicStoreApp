package com.example.technicstoreapp.ui.cart.order

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.technicstoreapp.databinding.RvOrderBinding
import com.example.technicstoreapp.domain.CartTechnicData

class OrderViewHolder(
    private val binding: RvOrderBinding
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: CartTechnicData) {
        binding.nameOrder.text = item.name
        binding.priceOrder.text = (item.price * item.count).toString() + RUB
        getPhoto(item.imageUrl, binding.imageTechnicOrder)
        binding.countOrder.text = item.count.toString() + COUNTER
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