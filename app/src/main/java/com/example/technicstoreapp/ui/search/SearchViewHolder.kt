package com.example.technicstoreapp.ui.search

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.technicstoreapp.databinding.RvCategoryPageBinding
import com.example.technicstoreapp.domain.TechnicData

class SearchViewHolder(
    private val binding: RvCategoryPageBinding,
    private val itemClick: (Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: TechnicData) {
        binding.categoryName.text = item.name
        getPhoto(item.colors.values.first(), binding.categoryImage)
        binding.categoryDescription.text = item.description
        binding.categoryPrice.text = item.price.toString() + RUB
        binding.categoryPrice.setOnClickListener {
            itemClick(item.id)
        }
        binding.root.setOnClickListener {
            itemClick(item.id)
        }
    }

    private fun getPhoto(url: String, image: ImageView) {
        Glide.with(image)
            .load(url)
            .into(image)
    }

    companion object {
        private const val RUB = " р."
    }
}