package com.example.technicstoreapp.ui.cart.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.CartTechnicData
import com.example.technicstoreapp.domain.RepositoryTech
import com.example.technicstoreapp.domain.RepositoryUser
import com.example.technicstoreapp.domain.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repositoryUser: RepositoryUser,
    private val repositoryTech: RepositoryTech
) : ViewModel() {

    private val _technicCartLiveData = MutableLiveData<List<CartTechnicData>>()
    val technicCartLiveData: LiveData<List<CartTechnicData>> get() = _technicCartLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _userLiveData = MutableLiveData<UserData>()
    val userLiveData: LiveData<UserData> get() = _userLiveData

    fun getTechnicCart() {
        _loadingLiveData.value = true
        viewModelScope.launch {
            _technicCartLiveData.value = repositoryTech.getAllTechnicFromCart()
            _loadingLiveData.value = false
        }
    }

    fun getUser() {
        viewModelScope.launch {
            _userLiveData.value = repositoryUser.getUserById()
        }
    }
}