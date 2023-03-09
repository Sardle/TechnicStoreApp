package com.example.technicstoreapp.ui.catalog

import androidx.recyclerview.widget.RecyclerView
import com.example.technicstoreapp.databinding.CatalogRecyclerBinding

class CatalogViewHolder(
    private val binding: CatalogRecyclerBinding,
    private val itemClick: (String) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: String) {
        binding.categoryName.apply {
            text = item
            setOnClickListener {
                itemClick(item)
            }
        }
    }
}