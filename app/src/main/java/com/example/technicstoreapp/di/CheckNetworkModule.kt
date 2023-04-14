package com.example.technicstoreapp.di

import android.content.Context
import com.example.technicstoreapp.ui.utils.CheckNetworkConnection
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CheckNetworkModule {

    @Provides
    @Singleton
    fun getNetworkConnection(context: Context): CheckNetworkConnection {
        return CheckNetworkConnection(context)
    }
}