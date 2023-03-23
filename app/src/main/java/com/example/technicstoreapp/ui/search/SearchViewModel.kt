package com.example.technicstoreapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technicstoreapp.domain.Repository
import com.example.technicstoreapp.domain.TechnicData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){

    private val _searchLiveData = MutableLiveData<List<TechnicData>>()
    val searchLiveData: LiveData<List<TechnicData>> get() = _searchLiveData

    fun getSearchResult(search: String) {
        _searchLiveData.value = repository.getSearchResult(search)
    }
}