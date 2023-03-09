package com.example.technicstoreapp.data.source

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val prefs: SharedPreferences
) {

    fun getUserToken(): String = prefs.getString(TOKEN_KEY, EMPTY_STRING).orEmpty()

    fun setUserToken(token: String) = prefs.edit {
        putString(TOKEN_KEY, token)
    }

    companion object {
        private const val EMPTY_STRING = ""

        private const val TOKEN_KEY = "TOKEN_KEY"
    }
}