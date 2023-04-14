package com.example.technicstoreapp.ui.profile.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.RepositoryUser
import com.example.technicstoreapp.domain.UserData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoUserViewModel @Inject constructor(
    private val repositoryUser: RepositoryUser
) : ViewModel() {

    private val _userLiveData = MutableLiveData<UserData>()
    val userLiveData: LiveData<UserData> get() = _userLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

    fun getUser() {
        _loadingLiveData.value = true
        viewModelScope.launch(exceptionHandler) {
            _userLiveData.value = repositoryUser.getUserById()
            _loadingLiveData.value = false
        }
    }

    fun deleteUser() {
        viewModelScope.launch(exceptionHandler) {
            repositoryUser.deleteUser()
        }
    }

    fun logOutUser() {
        repositoryUser.logOutUser()
    }
}
