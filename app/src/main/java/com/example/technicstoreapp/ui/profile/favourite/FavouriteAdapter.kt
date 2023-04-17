package com.example.technicstoreapp.ui.profile.favourite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.technicstoreapp.databinding.RvFavouriteBinding
import com.example.technicstoreapp.domain.models.TechnicData

class FavouriteAdapter(
    private val itemClick: (Int) -> Unit,
    private val removeClick: (Int, View) -> Unit
) : RecyclerView.Adapter<FavouriteViewHolder>() {

    private val listTechnicData = mutableListOf<TechnicData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = RvFavouriteBinding.inflate(layoutInflater, parent, false)
        return FavouriteViewHolder(item, itemClick, removeClick)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.onBind(listTechnicData[position])
    }

    override fun getItemCount(): Int = listTechnicData.size

    fun setItems(items: List<TechnicData>) {
        listTechnicData.clear()
        listTechnicData.addAll(items)
        notifyDataSetChanged()
    }
}