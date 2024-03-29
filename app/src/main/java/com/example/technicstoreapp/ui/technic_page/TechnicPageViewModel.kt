package com.example.technicstoreapp.ui.technic_page

import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.RepositoryTech
import com.example.technicstoreapp.domain.RepositoryUser
import com.example.technicstoreapp.domain.models.TechnicData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class TechnicPageViewModel @Inject constructor(
    private val repositoryTech: RepositoryTech,
    private val repositoryUser: RepositoryUser
) : ViewModel() {

    private val _technicLiveData = MutableLiveData<TechnicData>()
    val technicLiveData: LiveData<TechnicData> get() = _technicLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _checkLiveData = MutableLiveData<Boolean>()
    val checkLiveData: LiveData<Boolean> get() = _checkLiveData

    private val _countLiveData = MutableLiveData<Int>()
    val countLiveData: LiveData<Int> get() = _countLiveData

    private val _checkIsFavouriteLiveData = MutableLiveData<Boolean?>()
    val checkIsFavouriteLiveData: LiveData<Boolean?> get() = _checkIsFavouriteLiveData

    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

    fun checkIfElementExists(name: String, color: String) {
        viewModelScope.launch(exceptionHandler) {
            _checkLiveData.value = repositoryTech.checkIfElementExists(name, color)
        }
    }

    fun insertTechnicToCart(technicData: TechnicData, color: String) {
        viewModelScope.launch(exceptionHandler) {
            repositoryTech.insertTechnic(technicData, color)
            _countLiveData.value = repositoryTech.getAllTechnicFromCart().sumOf { it.count }
        }
    }

    fun addTechnicToFavourite(id: Int, button: Button) {
        button.isClickable = false
        viewModelScope.launch(exceptionHandler) {
            repositoryUser.addToFavourite(repositoryTech.getTechnicInfo(id))
            button.isClickable = true
        }
    }

    fun deleteFromFavourite(id: Int, button: Button) {
        button.isClickable = false
        viewModelScope.launch(exceptionHandler) {
            repositoryUser.removeFromFavourite(repositoryTech.getTechnicInfo(id))
            button.isClickable = true
        }
    }

    fun checkToFavourite(id: Int) {
        viewModelScope.launch(exceptionHandler) {
            _checkIsFavouriteLiveData.value =
                repositoryUser.checkToFavourite(repositoryTech.getTechnicInfo(id))
            _loadingLiveData.value = false
        }
    }

    fun getTechnicInfo(id: Int) {
        _loadingLiveData.value = true
        viewModelScope.launch(exceptionHandler) {
            _technicLiveData.value = repositoryTech.getTechnicInfo(id)
        }
    }
}