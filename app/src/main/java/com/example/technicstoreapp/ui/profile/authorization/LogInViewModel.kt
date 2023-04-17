package com.example.technicstoreapp.ui.profile.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.RepositoryUser
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogInViewModel @Inject constructor(
    private val repositoryUser: RepositoryUser
) : ViewModel() {

    private val _checkLiveData = MutableLiveData<Boolean>()
    val checkLiveData: LiveData<Boolean> get() = _checkLiveData

    fun checkLogInUser(number: String, password: String) {
        viewModelScope.launch {
            _checkLiveData.value = repositoryUser.checkLogInUser(number, password)
        }
    }
}