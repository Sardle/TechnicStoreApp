package com.example.technicstoreapp.ui.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.technicstoreapp.databinding.RvCatalogBinding

class CatalogAdapter(
    private val itemClick: (String) -> Unit
) : RecyclerView.Adapter<CatalogViewHolder>() {

    private val listCatalogData = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = RvCatalogBinding.inflate(layoutInflater, parent, false)
        return CatalogViewHolder(item, itemClick)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        holder.onBind(listCatalogData[position])
    }

    override fun getItemCount(): Int = listCatalogData.size

    fun setItems(items: List<String>) {
        listCatalogData.clear()
        listCatalogData.addAll(items)
        notifyDataSetChanged()
    }
}