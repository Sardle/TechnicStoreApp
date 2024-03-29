package com.example.technicstoreapp.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.technicstoreapp.databinding.RvCartBinding
import com.example.technicstoreapp.domain.models.CartTechnicData

class CartAdapter(
    private val itemClick: (Int, String) -> Unit,
    private val plusClick: (Int, String) -> Unit,
    private val minusClick: (Int, String) -> Unit,
    private val deleteClick: (Int, View, String) -> Unit,
    private val updateClick: () -> Unit,
) :
    RecyclerView.Adapter<CartViewHolder>() {

    private val listTechnicData = mutableListOf<CartTechnicData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = RvCartBinding.inflate(layoutInflater, parent, false)
        return CartViewHolder(item, itemClick, plusClick, minusClick, deleteClick, updateClick)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.onBind(listTechnicData[position])
    }

    override fun getItemCount(): Int = listTechnicData.size

    fun setItems(items: List<CartTechnicData>) {
        listTechnicData.clear()
        listTechnicData.addAll(items)
        notifyDataSetChanged()
    }
}