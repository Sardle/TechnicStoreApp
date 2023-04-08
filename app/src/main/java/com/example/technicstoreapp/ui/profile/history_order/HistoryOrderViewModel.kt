package com.example.technicstoreapp.ui.profile.history_order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.HistoryOrderItem
import com.example.technicstoreapp.domain.RepositoryUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryOrderViewModel @Inject constructor(
    private val repositoryUser: RepositoryUser
) : ViewModel() {

    private val _historyOrderLiveData = MutableLiveData<List<HistoryOrderItem>>()
    val historyOrderLiveData: LiveData<List<HistoryOrderItem>> get() = _historyOrderLiveData

    fun getHistoryOrderList() {
        viewModelScope.launch {
            _historyOrderLiveData.value = repositoryUser.getHistoryOrderData()
        }
    }
}