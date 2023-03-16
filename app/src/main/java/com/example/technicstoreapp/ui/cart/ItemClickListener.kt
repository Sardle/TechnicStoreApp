package com.example.technicstoreapp.ui.cart

interface ItemClickListener {

    suspend fun plus(id: Int)
}