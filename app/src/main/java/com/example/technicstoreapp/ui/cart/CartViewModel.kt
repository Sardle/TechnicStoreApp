package com.example.technicstoreapp.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.CartTechnicData
import com.example.technicstoreapp.domain.Repository
import com.example.technicstoreapp.domain.RepositoryUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: Repository,
    private val repositoryUser: RepositoryUser
) : ViewModel() {

    private val _technicCartLiveData = MutableLiveData<List<CartTechnicData>>()
    val technicCartLiveData: LiveData<List<CartTechnicData>> get() = _technicCartLiveData

    private val _priceLiveData = MutableLiveData<Double>()
    val priceLiveData: LiveData<Double> get() = _priceLiveData

    private val _priceForSetupLiveData = MutableLiveData<Double>()
    val priceForSetupLiveData: LiveData<Double> get() = _priceForSetupLiveData

    fun update() {
        viewModelScope.launch {
            repositoryUser.updateUser("1000")
        }
    }

    fun getPriceForSetup() {
        viewModelScope.launch {
            _priceForSetupLiveData.value = repository.getSumCurrentPrices()
        }
    }

    fun getTechnicCart() {
        viewModelScope.launch {
            _technicCartLiveData.value = repository.getAllTechnicFromCart()
        }
    }

    fun plusUnitTechnic(id: Int, color: String) {
        viewModelScope.launch {
            repository.plusUnitTechnic(id, color)
            _technicCartLiveData.value = repository.getAllTechnicFromCart()
        }
    }

    fun minusUnitTechnic(id: Int, color: String) {
        viewModelScope.launch {
            repository.removeUnitTechnic(id, color)
            _technicCartLiveData.value = repository.getAllTechnicFromCart()
        }
    }

    fun deleteUnitTechnic(id: Int, color: String) {
        viewModelScope.launch {
            repository.deleteTechnic(id, color)
            _technicCartLiveData.value = repository.getAllTechnicFromCart()
        }
    }

    fun getAllPrices() {
        viewModelScope.launch {
            _priceLiveData.value = repository.getSumCurrentPrices()
        }
    }
}