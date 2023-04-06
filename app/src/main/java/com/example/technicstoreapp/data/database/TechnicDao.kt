package com.example.technicstoreapp.data.database

import androidx.room.*

@Dao
interface TechnicDao {

    @Query("SELECT * FROM technic_cart")
    fun getAll(): List<TechnicEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(technic: TechnicEntity)

    @Delete
    fun delete(technic: List<TechnicEntity>)

    @Delete
    fun deleteTechnic(technic: TechnicEntity)

    @Update
    fun updateItem(technic: TechnicEntity)

    @Query("SELECT * FROM technic_cart WHERE id = :id AND color = :color")
    fun getItemById(id: Int, color: String): TechnicEntity?

    @Query("SELECT currentPrice FROM technic_cart")
    fun getCurrentPrices(): List<Double>
}