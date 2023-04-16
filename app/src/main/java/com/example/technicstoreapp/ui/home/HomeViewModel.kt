package com.example.technicstoreapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.models.NewsData
import com.example.technicstoreapp.domain.RepositoryTech
import com.example.technicstoreapp.domain.models.TechnicData
import com.example.technicstoreapp.ui.utils.CheckNetworkConnection
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repositoryTech: RepositoryTech,
    private val checkNetworkConnection: CheckNetworkConnection
) : ViewModel() {

    private val _technicLiveData = MutableLiveData<List<TechnicData>>()
    val technicLiveData: LiveData<List<TechnicData>> get() = _technicLiveData

    private val _newsLiveData = MutableLiveData<List<NewsData>>()
    val newsLiveData: LiveData<List<NewsData>> get() = _newsLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _checkNetworkLiveData = MutableLiveData<Boolean>()
    val checkNetworkLiveData: LiveData<Boolean> get() = _checkNetworkLiveData

    private val _countLiveData = MutableLiveData<Int>()
    val countLiveData: LiveData<Int> get() = _countLiveData

    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

    fun getTechnic() {
        _loadingLiveData.value = true
        viewModelScope.launch(exceptionHandler) {
            _technicLiveData.value = repositoryTech.getAllTechnic().take(5)
        }
    }

    fun setupBadgeCart() {
        viewModelScope.launch(exceptionHandler) {
            _countLiveData.value = repositoryTech.getAllTechnicFromCart().sumOf { it.count }
        }
    }

    fun getNews() {
        _loadingLiveData.value = true
        viewModelScope.launch(exceptionHandler) {
            _newsLiveData.value = repositoryTech.getNews()
            _loadingLiveData.value = false
        }
    }

    fun checkNetworkConnection() {
        checkNetworkConnection.isInternetAvailable().let {
            _checkNetworkLiveData.value = it
            if (!it) {
                _countLiveData.value = 0
            }
            _loadingLiveData.value = true
        }
    }

    fun setUserToken() {
        repositoryTech.setUserToken(USER_TOKEN)
    }

    companion object {
        private const val USER_TOKEN = "6e2215d6c8824752abea6defbc421007"
    }
}