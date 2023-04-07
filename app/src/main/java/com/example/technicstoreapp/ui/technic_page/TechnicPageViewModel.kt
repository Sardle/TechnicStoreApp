package com.example.technicstoreapp.ui.technic_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.RepositoryTech
import com.example.technicstoreapp.domain.TechnicData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TechnicPageViewModel @Inject constructor(
    private val repositoryTech: RepositoryTech
) : ViewModel() {

    private val _technicLiveData = MutableLiveData<TechnicData>()
    val technicLiveData: LiveData<TechnicData> get() = _technicLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _checkLiveData = MutableLiveData<Boolean>()
    val checkLiveData: LiveData<Boolean> get() = _checkLiveData

    fun checkIfElementExists(name: String, color: String) {
        viewModelScope.launch {
            _checkLiveData.value = repositoryTech.checkIfElementExists(name, color)
        }
    }

    fun insertTechnicToCart(technicData: TechnicData, color: String) {
        viewModelScope.launch {
            repositoryTech.insertTechnic(technicData, color)
        }
    }

    fun getTechnicInfo(id: Int){
        _loadingLiveData.value = true
        viewModelScope.launch {
            _technicLiveData.value = repositoryTech.getTechnicInfo(id)
            _loadingLiveData.value = false
        }
    }
}