package com.example.shopproject.viewmodels.product

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.product.Product
import com.example.shopproject.repositories.product.ProductRepository
import com.example.shopproject.utils.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductByCategoryViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {

    var categoryId: Long = ThisApp.categoryId
    var pageSize = 3
    var pageIndex = mutableStateOf(0)
    var dataList = mutableStateOf<List<Product>>(listOf())
    var isLoading = mutableStateOf(true)

    var scrollPosition = 0

    init {
        getProductByCategoryId(categoryId, pageIndex.value, pageSize) { response ->
            isLoading.value = false
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }

    private fun getProductByCategoryId(
        id: Long,
        pageIndex: Int,
        pageSize: Int,
        onResponse: (response: ServiceResponse<Product>) -> Unit
    ) {
        viewModelScope.launch {
            val response = repository.getProductByCategoryId(id, pageIndex, pageSize)
            onResponse(response)
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }

    private fun incrementPage() {
        pageIndex.value = pageIndex.value + 1
    }

    private fun appendItem(item: List<Product>) {
        dataList.value = dataList.value + item
    }

    fun onScrollPosition(position: Int) {
        scrollPosition = position
    }

    fun nextPage() {
        viewModelScope.launch {
            if ((scrollPosition + 1) >= (pageIndex.value * pageSize)) {
                isLoading.value = true
                incrementPage()
                if (pageIndex.value > 0) {
                    getProductByCategoryId(categoryId, pageIndex.value, pageSize) { response ->
                        if (response.status == "OK" && response.data!!.isNotEmpty()) {
                            appendItem(response.data!!)
                        }
                        isLoading.value = false
                    }
                }
            }
        }
    }
}