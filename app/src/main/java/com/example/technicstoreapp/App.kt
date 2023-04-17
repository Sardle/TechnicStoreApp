package com.example.technicstoreapp

import android.app.Application
import com.example.technicstoreapp.di.app.ApplicationComponent
import com.example.technicstoreapp.di.app.DaggerApplicationComponent

class App : Application() {
    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }
}