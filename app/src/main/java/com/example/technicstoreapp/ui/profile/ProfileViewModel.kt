package com.example.technicstoreapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technicstoreapp.domain.RepositoryUser
import com.example.technicstoreapp.domain.TechnicData
import com.example.technicstoreapp.domain.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repositoryUser: RepositoryUser
): ViewModel() {

    private val _checkLiveData = MutableLiveData<Boolean>()
    val checkLiveData: LiveData<Boolean> get() = _checkLiveData

    private val _userLiveData = MutableLiveData<UserData>()
    val userLiveData: LiveData<UserData> get() = _userLiveData

    fun checkAvailabilityUser() {
        _checkLiveData.value = repositoryUser.checkAvailabilityUser()
    }

    fun checkUser(): Boolean = repositoryUser.checkAvailabilityUser()

    fun getUser() {
        _userLiveData.value = repositoryUser.getUser()
    }
}