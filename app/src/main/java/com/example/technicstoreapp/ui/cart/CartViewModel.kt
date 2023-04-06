package com.example.technicstoreapp.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.CartTechnicData
import com.example.technicstoreapp.domain.RepositoryTech
import com.example.technicstoreapp.domain.RepositoryUser
import com.example.technicstoreapp.domain.use_cases.CalcDiscount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repositoryTech: RepositoryTech,
    private val repositoryUser: RepositoryUser,
    private val calcDiscount: CalcDiscount
) : ViewModel() {

    private val _technicCartLiveData = MutableLiveData<List<CartTechnicData>>()
    val technicCartLiveData: LiveData<List<CartTechnicData>> get() = _technicCartLiveData

    private val _priceLiveData = MutableLiveData<Double>()
    val priceLiveData: LiveData<Double> get() = _priceLiveData

    private val _checkLiveData = MutableLiveData<Boolean>()
    val checkLiveData: LiveData<Boolean> get() = _checkLiveData

    private val _checkListTechnicLiveData = MutableLiveData<Boolean>()
    val checkListTechnicLiveData: LiveData<Boolean> get() = _checkListTechnicLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    fun checkListTechnic() {
        viewModelScope.launch {
            _checkListTechnicLiveData.value = repositoryTech.checkListCart()
        }
    }

    fun checkUser() {
        viewModelScope.launch {
            _checkLiveData.value = repositoryUser.checkAvailabilityUser()
            _checkListTechnicLiveData.value = repositoryTech.checkListCart()
        }
    }

    fun update() {
        viewModelScope.launch {
            priceLiveData.value?.let { calcDiscount.calculatingDiscount(it) }
                ?.let { repositoryUser.updateUser(it) }
            _checkListTechnicLiveData.value = repositoryTech.checkListCart()
        }
    }

    fun getTechnicCart() {
        _loadingLiveData.value = true
        viewModelScope.launch {
            _priceLiveData.value = repositoryTech.getSumCurrentPrices()
            _technicCartLiveData.value = repositoryTech.getAllTechnicFromCart()
            _loadingLiveData.value = false
        }
    }

    fun plusUnitTechnic(id: Int, color: String) {
        viewModelScope.launch {
            repositoryTech.plusUnitTechnic(id, color)
            _priceLiveData.value = repositoryTech.getSumCurrentPrices()
            _technicCartLiveData.value = repositoryTech.getAllTechnicFromCart()
            _checkListTechnicLiveData.value = repositoryTech.checkListCart()
        }
    }

    fun minusUnitTechnic(id: Int, color: String) {
        viewModelScope.launch {
            repositoryTech.removeUnitTechnic(id, color)
            _priceLiveData.value = repositoryTech.getSumCurrentPrices()
            _technicCartLiveData.value = repositoryTech.getAllTechnicFromCart()
            _checkListTechnicLiveData.value = repositoryTech.checkListCart()
        }
    }

    fun deleteUnitTechnic(id: Int, color: String) {
        viewModelScope.launch {
            repositoryTech.deleteTechnic(id, color)
            _priceLiveData.value = repositoryTech.getSumCurrentPrices()
            _technicCartLiveData.value = repositoryTech.getAllTechnicFromCart()
            _checkListTechnicLiveData.value = repositoryTech.checkListCart()
        }
    }

    fun getAllPrices() {
        viewModelScope.launch {
            _priceLiveData.value = repositoryTech.getSumCurrentPrices()
        }
    }
}