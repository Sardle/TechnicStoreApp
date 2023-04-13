package com.example.technicstoreapp.di

import android.content.Context
import androidx.room.Room
import com.example.technicstoreapp.data.database.AppDataBase
import com.example.technicstoreapp.data.database.TechnicDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "database-name")
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: AppDataBase): TechnicDao = db.getTechnicDao()
}