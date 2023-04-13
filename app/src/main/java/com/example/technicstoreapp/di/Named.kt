package com.example.technicstoreapp.di

import javax.inject.Qualifier


@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class Named(
    val value: String = ""
)
