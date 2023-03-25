package com.example.technicstoreapp.ui.catalog.category_page

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.technicstoreapp.databinding.CategoryRecyclerPageBinding
import com.example.technicstoreapp.domain.TechnicData

class CategoryPageViewHolder(
    private val binding: CategoryRecyclerPageBinding,
    private val itemClick: (Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: TechnicData) {
        binding.categoryName.text = item.name
        getPoster(item.colorsAndImageUrl.values.first(), binding.categoryImage)
        binding.categoryDescription.text = item.description
        binding.categoryPrice.text = item.price.toString() + " р."
        binding.categoryPrice.setOnClickListener {
            itemClick(item.id)
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