package com.example.shopproject.viewmodels.site

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.site.Slider
import com.example.shopproject.repositories.site.SliderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SliderViewModel @Inject constructor(private val repository: SliderRepository) : ViewModel() {

    var dataList: MutableState<List<Slider>> = mutableStateOf(listOf())
    var isLoading = mutableStateOf(true)

    init {
        getSlider { response ->
            isLoading.value = false
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }

    fun getSlider(onResponse: (response: ServiceResponse<Slider>) -> Unit) {
        viewModelScope.launch {
            onResponse(repository.getSliders())

        }
    }

    fun getSliderById(id: Long, onResponse: (response: ServiceResponse<Slider>) -> Unit) {
        viewModelScope.launch {
            onResponse(repository.getSliderById(id))
        }
    }
}