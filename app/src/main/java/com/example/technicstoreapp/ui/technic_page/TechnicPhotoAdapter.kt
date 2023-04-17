package com.example.technicstoreapp.ui.technic_page

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TechnicPhotoAdapter(fragment: FragmentActivity, private val photoList: List<String>) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = photoList.size

    override fun createFragment(position: Int): Fragment {
        return PhotoTechnicPage(photoList[position])
    }
}