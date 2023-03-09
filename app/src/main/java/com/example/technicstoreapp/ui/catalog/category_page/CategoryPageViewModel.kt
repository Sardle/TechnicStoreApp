package com.example.technicstoreapp.ui.catalog.category_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technicstoreapp.domain.Repository
import com.example.technicstoreapp.domain.TechnicData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryPageViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _technicLiveData = MutableLiveData<List<TechnicData>>()
    val technicLiveData: LiveData<List<TechnicData>> get() = _technicLiveData

    fun getTechnic(category: String) {
        _technicLiveData.value = repository.getTechnicBasedFromCategory(category)
    }
}