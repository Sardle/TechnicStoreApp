package com.example.technicstoreapp.ui.profile.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.RepositoryUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val repositoryUser: RepositoryUser
) : ViewModel() {

    private val _checkLiveData = MutableLiveData<Boolean>()
    val checkLiveData: LiveData<Boolean> get() = _checkLiveData

    private val _chLiveData = MutableLiveData<Boolean>()
    val chLiveData: LiveData<Boolean> get() = _chLiveData

    fun checkLogInUser(number: String, password: String) {
        viewModelScope.launch {
            _checkLiveData.value = repositoryUser.checkLogInUser(number, password)
        }
    }

    fun checkAvailabilityUser() {
        _chLiveData.value = repositoryUser.checkAvailabilityUser()
    }
}