package com.example.technicstoreapp.di.app

import android.app.Application

class App : Application() {
    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }
}