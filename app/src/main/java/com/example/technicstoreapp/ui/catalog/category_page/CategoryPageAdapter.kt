package com.example.technicstoreapp.ui.catalog.category_page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.technicstoreapp.databinding.RvCategoryPageBinding
import com.example.technicstoreapp.domain.models.TechnicData

class CategoryPageAdapter(private val itemClick: (Int) -> Unit) :
    RecyclerView.Adapter<CategoryPageViewHolder>() {

    private val listTechnicData = mutableListOf<TechnicData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryPageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = RvCategoryPageBinding.inflate(layoutInflater, parent, false)
        return CategoryPageViewHolder(item, itemClick)
    }

    override fun onBindViewHolder(holder: CategoryPageViewHolder, position: Int) {
        holder.onBind(listTechnicData[position])
    }

    override fun getItemCount(): Int = listTechnicData.size

    fun setItems(items: List<TechnicData>) {
        listTechnicData.clear()
        listTechnicData.addAll(items)
        notifyDataSetChanged()
    }
}