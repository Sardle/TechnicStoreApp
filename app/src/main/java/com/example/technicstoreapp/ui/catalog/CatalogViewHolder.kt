package com.example.technicstoreapp.ui.catalog

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.technicstoreapp.databinding.CatalogRecyclerBinding

class CatalogViewHolder(
    private val binding: CatalogRecyclerBinding,
    private val itemClick: (String) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: String) {
        val urlAndText = item.split("|")
        getPoster(urlAndText.last(), binding.imageCatalog)
        binding.catalogName.text = urlAndText.first()
        binding.root.setOnClickListener {
            itemClick(urlAndText.first())
        }
    }

    private fun getPoster(url: String, image: ImageView) {
        Glide.with(image)
            .load(url)
            .into(image)
    }
}