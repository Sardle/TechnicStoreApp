package com.example.technicstoreapp.ui.technic_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicstoreapp.domain.Repository
import com.example.technicstoreapp.domain.TechnicData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TechnicPageViewModel @Inject constructor(
    private val repository: Repository
): ViewModel(){

    fun insertTechnicToCart(technicData: TechnicData) {
        viewModelScope.launch {
            repository.insertTechnic(technicData)
        }
    }

    fun getTechnicInfo(id: Int): TechnicData = repository.getTechnicInfo(id)

}