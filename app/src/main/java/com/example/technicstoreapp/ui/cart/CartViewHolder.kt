package com.example.technicstoreapp.ui.cart

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.technicstoreapp.databinding.CartRecyclerBinding
import com.example.technicstoreapp.domain.CartTechnicData

class CartViewHolder(
    private val binding: CartRecyclerBinding,
    private val itemClick: (Int) -> Unit,
    private val plusClick: (Int) -> Unit,
    private val minusClick: (Int) -> Unit,
    private val deleteClick: (Int) -> Unit,
    private val updateClick: () -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: CartTechnicData) {
        binding.count.text = item.count.toString()
        binding.cartName.text = item.name
        getPoster(item.imageUrl, binding.cartImage)
        binding.cartPrice.text = (item.price * item.count).toString() + " р."

        if (binding.count.text == "0") {
            deleteClick(item.id)
            updateClick()
        }

        binding.minus.setOnClickListener {
            val count = binding.count.text.toString().toInt() - 1
            binding.count.text = count.toString()
            minusClick(item.id)
            updateClick()
        }

        binding.plus.setOnClickListener {
            val count = binding.count.text.toString().toInt() + 1
            binding.count.text = count.toString()
            plusClick(item.id)
            updateClick()
        }

        binding.cartImage.setOnClickListener {
            itemClick(item.id)
        }
    }

    private fun getPoster(url: String, image: ImageView) {
        Glide.with(image)
            .load(url)
            .into(image)
    }

}