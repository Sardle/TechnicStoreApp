package com.example.technicstoreapp.ui.cart

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.technicstoreapp.databinding.CartRecyclerBinding
import com.example.technicstoreapp.domain.CartTechnicData

class CartViewHolder(
    private val binding: CartRecyclerBinding,
    private val itemClick: (Int, String) -> Unit,
    private val plusClick: (Int, String) -> Unit,
    private val minusClick: (Int, String) -> Unit,
    private val deleteClick: (Int, View, String) -> Unit,
    private val updateClick: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: CartTechnicData) {
        binding.count.text = item.count.toString()
        binding.cartName.text = item.name
        getPoster(item.imageUrl, binding.cartImage)
        binding.cartPrice.text = (item.price * item.count).toString() + " р."
        binding.cartColor.text = "Цвет: " + item.color

        if (binding.count.text == "0") {
            deleteClick(item.id, binding.root, item.color)
            updateClick()
        }

        binding.minus.setOnClickListener {
            minusClick(item.id, item.color)
            binding.count.text = (binding.count.text.toString().toInt() - 1).toString()
            updateClick()
        }

        binding.plus.setOnClickListener {
            plusClick(item.id, item.color)
            val count = binding.count.text.toString().toInt() + 1
            binding.count.text = count.toString()
            updateClick()
        }

        binding.root.setOnClickListener {
            itemClick(item.id, item.color)
        }
    }

    private fun getPoster(url: String, image: ImageView) {
        Glide.with(image).load(url).into(image)
    }

}