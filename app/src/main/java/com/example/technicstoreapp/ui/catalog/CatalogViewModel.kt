package com.example.technicstoreapp.ui.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.RepositoryTech
import com.example.technicstoreapp.ui.utils.CheckNetworkConnection
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val repositoryTech: RepositoryTech,
    private val checkNetworkConnection: CheckNetworkConnection
) : ViewModel() {

    private val _categoriesLiveData = MutableLiveData<List<String>>()
    val categoriesLiveData: LiveData<List<String>> get() = _categoriesLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _checkNetworkLiveData = MutableLiveData<Boolean>()
    val checkNetworkLiveData: LiveData<Boolean> get() = _checkNetworkLiveData

    private val _countLiveData = MutableLiveData<Int>()
    val countLiveData: LiveData<Int> get() = _countLiveData

    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

    fun getCategories() {
        _loadingLiveData.value = true
        viewModelScope.launch(exceptionHandler) {
            _categoriesLiveData.value = repositoryTech.getCategories()
            _loadingLiveData.value = false
        }
    }

    fun setupBadgeCart() {
        viewModelScope.launch(exceptionHandler) {
            _countLiveData.value = repositoryTech.getAllTechnicFromCart().sumOf { it.count }
        }
    }

    fun checkNetworkConnection() {
        _checkNetworkLiveData.value = checkNetworkConnection.isInternetAvailable()
    }
}