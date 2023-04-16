package com.example.technicstoreapp.ui.home.popular

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.technicstoreapp.databinding.RvPopularBinding
import com.example.technicstoreapp.domain.models.TechnicData

class PopularViewHolder(
    private val binding: RvPopularBinding,
    private val itemClick: (Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: TechnicData) {
        binding.name.text = item.name
        binding.price.text = item.price.toString() + RUB
        getPhoto(item.colors.values.first(), binding.imageTechnic)

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