package com.example.technicstoreapp.ui.cart.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.*
import com.example.technicstoreapp.domain.models.CartTechnicData
import com.example.technicstoreapp.domain.models.HistoryOrderData
import com.example.technicstoreapp.domain.models.UserData
import com.example.technicstoreapp.domain.use_cases.CalcDiscount
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class OrderViewModel @Inject constructor(
    private val repositoryUser: RepositoryUser,
    private val repositoryTech: RepositoryTech,
    private val calcDiscount: CalcDiscount
) : ViewModel() {

    private val _technicCartLiveData = MutableLiveData<List<CartTechnicData>>()
    val technicCartLiveData: LiveData<List<CartTechnicData>> get() = _technicCartLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _userLiveData = MutableLiveData<UserData>()
    val userLiveData: LiveData<UserData> get() = _userLiveData

    private val _priceLiveData = MutableLiveData<Double>()
    val priceLiveData: LiveData<Double> get() = _priceLiveData

    private val _priceWithDiscountLiveData = MutableLiveData<Int>()
    val priceWithDiscountLiveData: LiveData<Int> get() = _priceWithDiscountLiveData

    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }


    fun getTechnicCart() {
        viewModelScope.launch (exceptionHandler){
            _technicCartLiveData.value = repositoryTech.getAllTechnicFromCart()
        }
    }

    fun getUser() {
        _loadingLiveData.value = true
        viewModelScope.launch (exceptionHandler){
            _userLiveData.value = repositoryUser.getUserById()
            _loadingLiveData.value = false
        }
    }

    fun getTotalSum() {
        viewModelScope.launch (exceptionHandler){
            _priceLiveData.value = repositoryTech.getSumCurrentPrices()
        }
    }

    fun setupPriceWithDiscountLiveData() {
        _priceWithDiscountLiveData.value = -1
    }

    fun calculatingPriceWithDiscount(points: Int) {
        viewModelScope.launch (exceptionHandler){
            _priceWithDiscountLiveData.value = points
            _priceLiveData.value =
                repositoryTech.getSumCurrentPrices() - calcDiscount.calculatingDiscount(points)
        }
    }

    fun update(address: String, sum: Double) {
        viewModelScope.launch (exceptionHandler){
            val currentDate = Date()
            val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
            val formattedDate = dateFormat.format(currentDate)
            var points = calcDiscount.calculatingPoints(sum)
            if (priceWithDiscountLiveData.value!! > 0) {
                points = -priceWithDiscountLiveData.value!!
            }
            val historyOrderData = priceLiveData.value?.let {
                HistoryOrderData(
                    formattedDate,
                    repositoryTech.getAllTechnicFromCart(),
                    it
                )
            }
            historyOrderData?.let { repositoryUser.updateUser(it, address, points) }
            repositoryTech.deleteAllTechnicFromCart()
        }
    }
}