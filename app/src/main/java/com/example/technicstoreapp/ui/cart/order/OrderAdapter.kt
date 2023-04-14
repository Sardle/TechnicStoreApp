package com.example.technicstoreapp.ui.cart.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.technicstoreapp.databinding.RvOrderBinding
import com.example.technicstoreapp.domain.CartTechnicData

class OrderAdapter() :
    RecyclerView.Adapter<OrderViewHolder>() {

    private val listTechnicData = mutableListOf<CartTechnicData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemRvNewsBinding = RvOrderBinding.inflate(layoutInflater, parent, false)
        return OrderViewHolder(itemRvNewsBinding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.onBind(listTechnicData[position])
    }

    override fun getItemCount(): Int = listTechnicData.size

    fun setItems(items: List<CartTechnicData>) {
        listTechnicData.clear()
        listTechnicData.addAll(items)
        notifyDataSetChanged()
    }
}