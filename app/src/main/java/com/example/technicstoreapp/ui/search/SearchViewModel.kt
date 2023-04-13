package com.example.technicstoreapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.RepositoryTech
import com.example.technicstoreapp.domain.TechnicData
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repositoryTech: RepositoryTech
) : ViewModel() {

    private val _searchLiveData = MutableLiveData<List<TechnicData>>()
    val searchLiveData: LiveData<List<TechnicData>> get() = _searchLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _checkEmptyLiveData = MutableLiveData<Boolean>()
    val checkEmptyLiveData: LiveData<Boolean> get() = _checkEmptyLiveData

    fun getSearchResult(search: String) {
        if (search != "") {
            _loadingLiveData.value = true
            viewModelScope.launch {
                _searchLiveData.value = repositoryTech.getSearchResult(search)
                _loadingLiveData.value = false
                _checkEmptyLiveData.value = repositoryTech.getSearchResult(search).isEmpty()
            }
        } else {
            _searchLiveData.value = emptyList()
        }
    }
}