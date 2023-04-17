package com.example.technicstoreapp.ui.home.news_recycler

import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.technicstoreapp.databinding.RvNewsBinding
import com.example.technicstoreapp.domain.models.NewsData

class NewsViewHolder(private val binding: RvNewsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: NewsData) {
        binding.title.text = item.title
        binding.author.text = item.author
        getPhoto(item.urlToImage, binding.image)

        itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(item.url)
            binding.title.context.startActivity(intent)
        }
    }

    private fun getPhoto(url: String, image: ImageView) {
        Glide.with(image)
            .load(url)
            .into(image)
    }
}