package com.example.technicstoreapp.data.source

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val prefs: SharedPreferences
) {

    fun getUserId(): String = prefs.getString(USER_ID, EMPTY_STRING).orEmpty()

    fun setUserId(id: String) = prefs.edit {
        putString(USER_ID, id)
    }

    companion object {
        private const val EMPTY_STRING = ""

        private const val USER_ID = "USER_ID"
    }
}