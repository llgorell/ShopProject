package com.example.shopproject.viewmodels.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.site.Content
import com.example.shopproject.repositories.site.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(private val repository : ContentRepository) : ViewModel() {

    fun getContent(onResponse : (response : ServiceResponse<Content>) -> Unit){
        viewModelScope.launch {
            onResponse(repository.getContent())
        }
    }

    fun getContentById(title : String,onResponse :(response : ServiceResponse<Content>) -> Unit){
        viewModelScope.launch {
            onResponse(repository.getContentByTitle(title))
        }
    }
}