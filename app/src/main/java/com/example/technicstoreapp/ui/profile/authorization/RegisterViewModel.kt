package com.example.technicstoreapp.ui.profile.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.RepositoryUser
import com.example.technicstoreapp.domain.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repositoryUser: RepositoryUser
): ViewModel() {

    private val _checkLiveData = MutableLiveData<Boolean>()
    val checkLiveData: LiveData<Boolean> get() = _checkLiveData

    fun addUserToList(userData: UserData) {
        viewModelScope.launch {
            _checkLiveData.value = repositoryUser.createUser(userData)
        }
    }
}