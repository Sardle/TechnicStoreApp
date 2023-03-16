package com.example.technicstoreapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(tableName = "technic_cart", primaryKeys = ["name", "color"])
data class TechnicEntity(
    @ColumnInfo val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val imageUrl: String,
    @ColumnInfo val description: String,
    @ColumnInfo var price: Double,
    @ColumnInfo var currentPrice: Double,
    @ColumnInfo val category: String,
    @ColumnInfo val color: String,
    @ColumnInfo var count: Int
)