package com.example.technicstoreapp.di

import com.example.technicstoreapp.data.RepositoryTechImpl
import com.example.technicstoreapp.data.RepositoryUserImpl
import com.example.technicstoreapp.domain.RepositoryTech
import com.example.technicstoreapp.domain.RepositoryUser
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun getRepository(impl: RepositoryTechImpl): RepositoryTech

    @Binds
    abstract fun getUserRepository(impl: RepositoryUserImpl): RepositoryUser
}