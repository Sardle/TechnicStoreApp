package com.example.technicstoreapp.ui.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technicstoreapp.domain.Repository
import com.example.technicstoreapp.domain.TechnicData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _categoriesLiveData = MutableLiveData<List<String>>()
    val categoriesLiveData: LiveData<List<String>> get() = _categoriesLiveData

    fun getCategories() {
        _categoriesLiveData.value = repository.getCategories()
    }
}