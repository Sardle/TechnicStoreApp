package com.example.technicstoreapp.data.source

import com.example.technicstoreapp.data.database.TechnicDao
import com.example.technicstoreapp.data.database.TechnicEntity
import javax.inject.Inject

class DataBaseSource @Inject constructor(
    private val dao: TechnicDao
) {

    fun getAll(): List<TechnicEntity> = dao.getAll()

    fun insert(technic: TechnicEntity) = dao.insert(technic)

    fun delete(technic: List<TechnicEntity>) = dao.delete(technic)

    fun updateItem(technic: TechnicEntity) = dao.updateItem(technic)

    fun getItemById(id: Int): TechnicEntity? = dao.getItemById(id)

    fun deleteTechnic(technic: TechnicEntity) = dao.deleteTechnic(technic)

    fun getCurrentPrices(): List<Double> = dao.getCurrentPrices()
}