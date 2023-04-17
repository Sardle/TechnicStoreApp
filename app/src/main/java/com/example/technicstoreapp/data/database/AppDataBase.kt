package com.example.technicstoreapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TechnicEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getTechnicDao(): TechnicDao
}