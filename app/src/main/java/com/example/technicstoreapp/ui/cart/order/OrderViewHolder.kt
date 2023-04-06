package com.example.technicstoreapp.ui.cart.order

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.technicstoreapp.databinding.OrderRecyclerBinding
import com.example.technicstoreapp.domain.CartTechnicData

class OrderViewHolder(
    private val binding: OrderRecyclerBinding
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: CartTechnicData) {
        binding.nameOrder.text = item.name
        binding.priceOrder.text = (item.price * item.count).toString() + " р."
        getPoster(item.imageUrl, binding.imageTechnicOrder)
        binding.countOrder.text = item.count.toString() + " шт."
    }

    private fun getPoster(url: String, image: ImageView) {
        Glide.with(image)
            .load(url)
            .into(image)
    }
}