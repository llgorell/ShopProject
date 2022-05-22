package com.example.shopproject.viewmodels.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.site.Blog
import com.example.shopproject.repositories.site.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogViewModel @Inject constructor(private val repository : BlogRepository) : ViewModel() {

    fun getBlog(onResponse : (response : ServiceResponse<Blog>) -> Unit){
        viewModelScope.launch {
            onResponse(repository.getBlog())
        }
    }

    fun getBlogById(id : Long,onResponse :(response : ServiceResponse<Blog>) -> Unit){
        viewModelScope.launch {
            onResponse(repository.getBlogById(id))
        }
    }
}