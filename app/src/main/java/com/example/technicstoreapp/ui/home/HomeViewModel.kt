package com.example.technicstoreapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.NewsData
import com.example.technicstoreapp.domain.Repository
import com.example.technicstoreapp.domain.TechnicData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _technicLiveData = MutableLiveData<List<TechnicData>>()
    val technicLiveData: LiveData<List<TechnicData>> get() = _technicLiveData

    private val _newsLiveData = MutableLiveData<List<NewsData>>()
    val newsLiveData: LiveData<List<NewsData>> get() = _newsLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    fun getTechnic() {
        _loadingLiveData.value = true
        viewModelScope.launch {
            _technicLiveData.value = repository.getAllTechnic()
            _loadingLiveData.value = false
        }
    }

    fun getNews() {
        _loadingLiveData.value = true
        viewModelScope.launch {
            _newsLiveData.value = repository.getNews()
            _loadingLiveData.value = false
        }
    }

    fun setUserToken() {
        repository.setUserToken(USER_TOKEN)
    }

    companion object {
        private const val USER_TOKEN = "6e2215d6c8824752abea6defbc421007"
    }
}