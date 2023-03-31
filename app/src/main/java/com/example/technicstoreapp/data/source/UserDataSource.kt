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

    fun getUserId(): String = prefs.getString(USER_ID, EMPTY_STRING).orEmpty()

    fun setUserId(id: String) = prefs.edit {
        putString(USER_ID, id)
    }

    companion object {
        private const val EMPTY_STRING = ""

        private const val TOKEN_KEY = "TOKEN_KEY"

        private const val USER_ID = "USER_ID"
    }
}