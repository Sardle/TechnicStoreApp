package com.example.technicstoreapp.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.CartTechnicData
import com.example.technicstoreapp.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _technicCartLiveData = MutableLiveData<List<CartTechnicData>>()
    val technicCartLiveData: LiveData<List<CartTechnicData>> get() = _technicCartLiveData

    private val _priceLiveData = MutableLiveData<Double>()
    val priceLiveData: LiveData<Double> get() = _priceLiveData

    fun getTechnicCart() {
        viewModelScope.launch {
            _technicCartLiveData.value = repository.getAllTechnicFromCart()
        }
    }

    fun plusUnitTechnic(id: Int) {
        viewModelScope.launch {
            repository.insertTechnic(repository.getTechnicInfo(id))
            _technicCartLiveData.value = repository.getAllTechnicFromCart()
        }
    }

    fun minusUnitTechnic(id: Int) {
        viewModelScope.launch {
            repository.removeUnitTechnic(repository.getTechnicInfo(id))
            _technicCartLiveData.value = repository.getAllTechnicFromCart()
        }
    }

    fun deleteUnitTechnic(id: Int) {
        viewModelScope.launch {
            repository.deleteTechnic(repository.getTechnicInfo(id))
            _technicCartLiveData.value = repository.getAllTechnicFromCart()
        }
    }

    fun getAllPrices() {
        viewModelScope.launch {
            _priceLiveData.value = repository.getSumCurrentPrices()
        }
    }
}