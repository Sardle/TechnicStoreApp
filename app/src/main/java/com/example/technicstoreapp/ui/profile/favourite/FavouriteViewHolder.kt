package com.example.technicstoreapp.ui.profile.favourite

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.technicstoreapp.databinding.CategoryRecyclerPageBinding
import com.example.technicstoreapp.databinding.RvFavouriteBinding
import com.example.technicstoreapp.domain.TechnicData

class FavouriteViewHolder(
    private val binding: RvFavouriteBinding,
    private val itemClick: (Int) -> Unit,
    private val removeClick: (Int, View) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: TechnicData) {
        binding.favouriteName.text = item.name
        getPoster(item.colors.values.first(), binding.favouriteImage)
        binding.favouriteDescription.text = item.description
        binding.favouritePrice.text = item.price.toString() + " р."

        binding.clear.setOnClickListener {
            removeClick(item.id, binding.root)
        }
        binding.favouritePrice.setOnClickListener {
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