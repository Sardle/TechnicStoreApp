package com.example.technicstoreapp.ui.profile.authorization

import androidx.lifecycle.ViewModel
import com.example.technicstoreapp.domain.RepositoryUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val repositoryUser: RepositoryUser
): ViewModel() {

    fun checkLogInUser(number: String, password: String): Boolean {
        return if (repositoryUser.checkLogInUser(number, password) != null) {
            println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC${repositoryUser.checkLogInUser(number, password)}")
            repositoryUser.setPrefsUserId(number, password)
            true
        } else {
            false
        }
    }
}