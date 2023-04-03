package com.example.technicstoreapp.ui.profile.user

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
class InfoUserViewModel @Inject constructor(
    private val repositoryUser: RepositoryUser
) : ViewModel() {

    private val _userLiveData = MutableLiveData<UserData>()
    val userLiveData: LiveData<UserData> get() = _userLiveData

    fun getUser() {
        viewModelScope.launch {
            _userLiveData.value = repositoryUser.getUserById()
        }
    }

    fun logOutUser() {
        repositoryUser.logOutUser()
    }
}
