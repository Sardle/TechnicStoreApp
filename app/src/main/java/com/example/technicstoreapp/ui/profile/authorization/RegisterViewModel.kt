package com.example.technicstoreapp.ui.profile.authorization

import androidx.lifecycle.ViewModel
import com.example.technicstoreapp.domain.RepositoryUser
import com.example.technicstoreapp.domain.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repositoryUser: RepositoryUser
): ViewModel() {

    fun addUserToList(userData: UserData) {
        repositoryUser.addUserToList(userData)
    }
}