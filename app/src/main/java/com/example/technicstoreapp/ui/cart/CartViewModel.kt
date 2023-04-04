package com.example.technicstoreapp.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.CartTechnicData
import com.example.technicstoreapp.domain.RepositoryTech
import com.example.technicstoreapp.domain.RepositoryUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repositoryTech: RepositoryTech,
    private val repositoryUser: RepositoryUser
) : ViewModel() {

    private val _technicCartLiveData = MutableLiveData<List<CartTechnicData>>()
    val technicCartLiveData: LiveData<List<CartTechnicData>> get() = _technicCartLiveData

    private val _priceLiveData = MutableLiveData<Double>()
    val priceLiveData: LiveData<Double> get() = _priceLiveData

    private val _checkLiveData = MutableLiveData<Boolean>()
    val checkLiveData: LiveData<Boolean> get() = _checkLiveData

    fun checkAvailabilityUser() {
        _checkLiveData.value = repositoryUser.checkAvailabilityUser()
    }

    fun update() {
        viewModelScope.launch {
            repositoryUser.updateUser("1000")
        }
    }

    fun getTechnicCart() {
        viewModelScope.launch {
            _priceLiveData.value = repositoryTech.getSumCurrentPrices()
            _technicCartLiveData.value = repositoryTech.getAllTechnicFromCart()
        }
    }

    fun plusUnitTechnic(id: Int, color: String) {
        viewModelScope.launch {
            repositoryTech.plusUnitTechnic(id, color)
            _priceLiveData.value = repositoryTech.getSumCurrentPrices()
            _technicCartLiveData.value = repositoryTech.getAllTechnicFromCart()
        }
    }

    fun minusUnitTechnic(id: Int, color: String) {
        viewModelScope.launch {
            repositoryTech.removeUnitTechnic(id, color)
            _priceLiveData.value = repositoryTech.getSumCurrentPrices()
            _technicCartLiveData.value = repositoryTech.getAllTechnicFromCart()
        }
    }

    fun deleteUnitTechnic(id: Int, color: String) {
        viewModelScope.launch {
            repositoryTech.deleteTechnic(id, color)
            _priceLiveData.value = repositoryTech.getSumCurrentPrices()
            _technicCartLiveData.value = repositoryTech.getAllTechnicFromCart()
        }
    }

    fun getAllPrices() {
        viewModelScope.launch {
            _priceLiveData.value = repositoryTech.getSumCurrentPrices()
        }
    }
}