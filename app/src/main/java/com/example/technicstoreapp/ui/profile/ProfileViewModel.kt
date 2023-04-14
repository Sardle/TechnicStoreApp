package com.example.technicstoreapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.RepositoryUser
import com.example.technicstoreapp.domain.UserData
import com.example.technicstoreapp.ui.utils.CheckNetworkConnection
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repositoryUser: RepositoryUser,
    private val checkNetworkConnection: CheckNetworkConnection
) : ViewModel() {

    private val _userLiveData = MutableLiveData<UserData>()
    val userLiveData: LiveData<UserData> get() = _userLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _checkNetworkLiveData = MutableLiveData<Boolean>()
    val checkNetworkLiveData: LiveData<Boolean> get() = _checkNetworkLiveData

    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

    fun checkUser(): Boolean = repositoryUser.checkAvailabilityUser()

    fun getUser() {
        _loadingLiveData.value = true
        viewModelScope.launch(exceptionHandler) {
            _userLiveData.value = repositoryUser.getUserById()
            _loadingLiveData.value = false
        }
    }

    fun checkNetworkConnection() {
        _checkNetworkLiveData.value = checkNetworkConnection.isInternetAvailable()
    }
}