package com.example.shopproject.viewmodels.product

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.product.Product
import com.example.shopproject.model.product.ProductCategory
import com.example.shopproject.model.product.ProductColor
import com.example.shopproject.model.site.Slider
import com.example.shopproject.repositories.product.ColorRepository
import com.example.shopproject.repositories.product.ProductCategoryRepository
import com.example.shopproject.repositories.product.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {

    var dataList: MutableState<List<Product>> = mutableStateOf(listOf())
    var isLoading = mutableStateOf(true)
    var product = mutableStateOf<Product?>(null)

    init {
        getProducts(pageIndex = 0, pageSize = 6) { response ->
            isLoading.value=false
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }

    fun getProducts(pageIndex : Int,pageSize:Int,onResponse: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getProduct(pageIndex,pageSize)
            onResponse(response)
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }

    fun getProductById(id: Long, onResponse: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            onResponse(repository.getProductById(id))
        }
    }

    fun getNewProduct(onResponse: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getNewProduct()
            onResponse(response)
            if (response.status == "OK") {
                dataList.value = response.data!!

            }
        }
    }

    fun getPopularProduct(onResponse: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getPopularProduct()
            onResponse(response)
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }

}