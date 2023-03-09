package com.example.technicstoreapp.ui.home.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.technicstoreapp.databinding.PopularRecyclerBinding
import com.example.technicstoreapp.domain.TechnicData

class PopularAdapter(private val itemClick: (String, String, String, String) -> Unit) :
    RecyclerView.Adapter<PopularViewHolder>() {

    private val listTechnicData = mutableListOf<TechnicData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemRvNewsBinding = PopularRecyclerBinding.inflate(layoutInflater, parent, false)
        return PopularViewHolder(itemRvNewsBinding, itemClick)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.onBind(listTechnicData[position])
    }

    override fun getItemCount(): Int = listTechnicData.size

    fun setItems(items: List<TechnicData>) {
        listTechnicData.clear()
        listTechnicData.addAll(items)
        notifyDataSetChanged()
    }
}