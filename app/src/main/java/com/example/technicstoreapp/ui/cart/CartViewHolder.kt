package com.example.technicstoreapp.ui.cart

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.CartRecyclerBinding
import com.example.technicstoreapp.domain.CartTechnicData

class CartViewHolder(
    private val binding: CartRecyclerBinding,
    private val itemClick: (Int) -> Unit,
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
            val count = binding.count.text.toString().toInt() - 1
            binding.count.text = count.toString()
            minusClick(item.id, item.color)
            updateClick()
        }

        binding.plus.setOnClickListener {
            val count = binding.count.text.toString().toInt() + 1
            binding.count.text = count.toString()
            plusClick(item.id, item.color)
            updateClick()
        }

        binding.root.setOnClickListener {
            itemClick(item.id)
        }
    }

    private fun getPoster(url: String, image: ImageView) {
        Glide.with(image)
            .load(url)
            .into(image)
    }

}