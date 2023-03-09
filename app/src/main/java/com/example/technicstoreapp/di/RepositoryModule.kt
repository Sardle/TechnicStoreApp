package com.example.technicstoreapp.di

import com.example.technicstoreapp.data.RepositoryImpl
import com.example.technicstoreapp.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun getRepository(impl: RepositoryImpl): Repository
}