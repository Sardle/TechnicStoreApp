package com.example.technicstoreapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.R
import com.example.technicstoreapp.domain.NewsData
import com.example.technicstoreapp.domain.RepositoryTech
import com.example.technicstoreapp.domain.TechnicData
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repositoryTech: RepositoryTech
) : ViewModel() {

    private val _technicLiveData = MutableLiveData<List<TechnicData>>()
    val technicLiveData: LiveData<List<TechnicData>> get() = _technicLiveData

    private val _newsLiveData = MutableLiveData<List<NewsData>>()
    val newsLiveData: LiveData<List<NewsData>> get() = _newsLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _countLiveData = MutableLiveData<Int>()
    val countLiveData: LiveData<Int> get() = _countLiveData

    fun getTechnic() {
        _loadingLiveData.value = true
        viewModelScope.launch {
            _technicLiveData.value = repositoryTech.getAllTechnic().take(5)
        }
    }

    fun setupBadgeCart() {
        viewModelScope.launch {
            _countLiveData.value = repositoryTech.getAllTechnicFromCart().sumOf { it.count }
        }
    }

    fun getNews() {
        _loadingLiveData.value = true
        viewModelScope.launch {
            _newsLiveData.value = repositoryTech.getNews()
            _loadingLiveData.value = false
        }
    }

    fun setUserToken() {
        repositoryTech.setUserToken(USER_TOKEN)
    }

    companion object {
        private const val USER_TOKEN = "6e2215d6c8824752abea6defbc421007"
    }
}