package com.example.technicstoreapp.ui.catalog

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.technicstoreapp.databinding.RvCatalogBinding

class CatalogViewHolder(
    private val binding: RvCatalogBinding,
    private val itemClick: (String) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: String) {
        val urlAndText = item.split("|")
        getPhoto(urlAndText.last(), binding.imageCatalog)
        binding.catalogName.text = urlAndText.first()
        binding.root.setOnClickListener {
            itemClick(urlAndText.first())
        }
    }

    private fun getPhoto(url: String, image: ImageView) {
        Glide.with(image)
            .load(url)
            .into(image)
    }
}