package com.example.technicstoreapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.RepositoryUser
import com.example.technicstoreapp.domain.UserData
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repositoryUser: RepositoryUser
) : ViewModel() {

    private val _checkLiveData = MutableLiveData<Boolean>()
    val checkLiveData: LiveData<Boolean> get() = _checkLiveData

    private val _userLiveData = MutableLiveData<UserData>()
    val userLiveData: LiveData<UserData> get() = _userLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    fun checkAvailabilityUser() {
        _checkLiveData.value = repositoryUser.checkAvailabilityUser()
    }

    fun checkUser(): Boolean = repositoryUser.checkAvailabilityUser()

    fun getUser() {
        _loadingLiveData.value = true
        viewModelScope.launch {
            _userLiveData.value = repositoryUser.getUserById()
            _loadingLiveData.value = false
        }
    }
}