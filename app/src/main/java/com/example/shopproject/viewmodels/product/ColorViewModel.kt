package com.example.shopproject.viewmodels.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.product.ProductColor
import com.example.shopproject.repositories.product.ColorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorViewModel @Inject constructor(private val repository : ColorRepository) : ViewModel() {

    fun getColors(onResponse : (response : ServiceResponse<ProductColor>) -> Unit){
        viewModelScope.launch {
            onResponse(repository.getColors())
        }
    }

    fun getColorById(id : Long,onResponse :(response : ServiceResponse<ProductColor>) -> Unit){
        viewModelScope.launch {
            onResponse(repository.getColorById(id))
        }
    }
}