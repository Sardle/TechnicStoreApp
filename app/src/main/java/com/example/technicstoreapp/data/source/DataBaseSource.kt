package com.example.technicstoreapp.data.source

import com.example.technicstoreapp.data.database.TechnicDao
import com.example.technicstoreapp.data.database.TechnicEntity
import javax.inject.Inject

class DataBaseSource @Inject constructor(
    private val dao: TechnicDao
) {

    fun getAll(): List<TechnicEntity> = dao.getAll()

    fun insert(technic: TechnicEntity) = dao.insert(technic)

    fun checkIfElementExists(name: String, color: String): Int = dao.checkIfElementExists(name, color)

    fun delete(technic: List<TechnicEntity>) = dao.delete(technic)

    fun updateItem(technic: TechnicEntity) = dao.updateItem(technic)

    fun getItemById(id: Int, color: String): TechnicEntity? = dao.getItemById(id, color)

    fun deleteTechnic(technic: TechnicEntity) = dao.deleteTechnic(technic)

    fun getCurrentPrices(): List<Double> = dao.getCurrentPrices()
}