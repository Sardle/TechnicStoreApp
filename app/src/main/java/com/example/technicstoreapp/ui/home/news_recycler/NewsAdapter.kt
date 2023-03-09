package com.example.technicstoreapp.ui.home.news_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.technicstoreapp.databinding.NewsRecyclerBinding
import com.example.technicstoreapp.domain.NewsData

class NewsAdapter() : RecyclerView.Adapter<NewsViewHolder>() {

    private val listNewsData = mutableListOf<NewsData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemRvNewsBinding = NewsRecyclerBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(itemRvNewsBinding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(listNewsData[position])
    }

    override fun getItemCount(): Int = listNewsData.size

    fun setItems(items: List<NewsData>) {
        listNewsData.clear()
        listNewsData.addAll(items)
        notifyDataSetChanged()
    }
}