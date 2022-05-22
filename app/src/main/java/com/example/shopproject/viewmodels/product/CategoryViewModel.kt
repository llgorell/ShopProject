package com.example.shopproject.viewmodels.product

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.product.ProductCategory
import com.example.shopproject.model.product.ProductColor
import com.example.shopproject.repositories.product.ColorRepository
import com.example.shopproject.repositories.product.ProductCategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository : ProductCategoryRepository) : ViewModel() {

    val listDataState = mutableStateOf<List<ProductCategory>>(listOf())
    var isLoading = mutableStateOf(true)
    init {
       getCategories {response->
           isLoading.value = false
           if (response.status == "OK")
            listDataState.value = response.data!!
        }
    }

   private fun getCategories(onResponse : (response : ServiceResponse<ProductCategory>) -> Unit){
        viewModelScope.launch {
            onResponse(repository.getCategory())
        }
    }

    fun getCategoryById(id : Long,onResponse :(response : ServiceResponse<ProductCategory>) -> Unit){
        viewModelScope.launch {
            onResponse(repository.getCategoryById(id))
        }
    }
}