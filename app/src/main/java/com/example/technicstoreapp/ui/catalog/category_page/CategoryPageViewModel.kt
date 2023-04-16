package com.example.technicstoreapp.ui.catalog.category_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.RepositoryTech
import com.example.technicstoreapp.domain.models.TechnicData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryPageViewModel @Inject constructor(
    private val repositoryTech: RepositoryTech
) : ViewModel() {

    private val _technicLiveData = MutableLiveData<List<TechnicData>>()
    val technicLiveData: LiveData<List<TechnicData>> get() = _technicLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

    fun getTechnic(category: String) {
        _loadingLiveData.value = true
        viewModelScope.launch (exceptionHandler){
            _technicLiveData.value = repositoryTech.getTechnicBasedFromCategory(category)
            _loadingLiveData.value = false
        }
    }

    fun getTechnicSorted(category: String) {
        _loadingLiveData.value = true
        viewModelScope.launch (exceptionHandler){
            _technicLiveData.value = repositoryTech.getTechnicBasedFromCategory(category).sortedBy { it.price }
            _loadingLiveData.value = false
        }
    }

    fun getTechnicSortedDescending(category: String) {
        _loadingLiveData.value = true
        viewModelScope.launch (exceptionHandler){
            _technicLiveData.value = repositoryTech.getTechnicBasedFromCategory(category).sortedByDescending { it.price }
            _loadingLiveData.value = false
        }
    }
}