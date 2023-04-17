package com.example.technicstoreapp.ui.profile.favourite

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.technicstoreapp.databinding.RvFavouriteBinding
import com.example.technicstoreapp.domain.models.TechnicData

class FavouriteViewHolder(
    private val binding: RvFavouriteBinding,
    private val itemClick: (Int) -> Unit,
    private val removeClick: (Int, View) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: TechnicData) {
        binding.favouriteName.text = item.name
        getPhoto(item.colors.values.first(), binding.favouriteImage)
        binding.favouriteDescription.text = item.description
        binding.favouritePrice.text = item.price.toString() + RUB

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

    private fun getPhoto(url: String, image: ImageView) {
        Glide.with(image)
            .load(url)
            .into(image)
    }

    companion object {
        private const val RUB = " р."
    }
}