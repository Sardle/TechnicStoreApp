package com.example.technicstoreapp.ui.profile.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.RepositoryTech
import com.example.technicstoreapp.domain.RepositoryUser
import com.example.technicstoreapp.domain.TechnicData
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(
    private val repositoryUser: RepositoryUser,
    private val repositoryTech: RepositoryTech
) : ViewModel() {

    private val _favouriteTechnicLiveData = MutableLiveData<List<TechnicData>>()
    val favouriteTechnicLiveData: LiveData<List<TechnicData>> get() = _favouriteTechnicLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _checkLiveData = MutableLiveData<Boolean>()
    val checkLiveData: LiveData<Boolean> get() = _checkLiveData

    fun checkFavouriteListIsEmpty() {
        viewModelScope.launch {
            _checkLiveData.value = repositoryUser.getFavouriteTechnic().isEmpty()
        }
    }

    fun getFavouriteTechnic() {
        _loadingLiveData.value = true
        viewModelScope.launch {
            _favouriteTechnicLiveData.value = repositoryUser.getFavouriteTechnic()
            _loadingLiveData.value = false
        }
    }

    fun removeFromFavourite(id: Int) {
        viewModelScope.launch {
            repositoryUser.removeFromFavourite(repositoryTech.getTechnicInfo(id))
            _favouriteTechnicLiveData.value = repositoryUser.getFavouriteTechnic()
            _checkLiveData.value = repositoryUser.getFavouriteTechnic().isEmpty()
        }
    }
}