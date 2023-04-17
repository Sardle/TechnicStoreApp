package com.example.technicstoreapp.di.module

import com.example.technicstoreapp.data.RepositoryTechImpl
import com.example.technicstoreapp.data.RepositoryUserImpl
import com.example.technicstoreapp.domain.RepositoryTech
import com.example.technicstoreapp.domain.RepositoryUser
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun getRepository(impl: RepositoryTechImpl): RepositoryTech

    @Binds
    @Singleton
    abstract fun getUserRepository(impl: RepositoryUserImpl): RepositoryUser
}