package com.example.shopproject.viewmodels.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.customer.User
import com.example.shopproject.model.customer.UserVm
import com.example.shopproject.model.invoice.Invoice
import com.example.shopproject.model.product.ProductColor
import com.example.shopproject.repositories.customer.UserRepository
import com.example.shopproject.repositories.invoice.InvoiceRepository
import com.example.shopproject.repositories.product.ColorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository : UserRepository) : ViewModel() {

    //todo fix Token
    fun getUser(onResponse : (response : ServiceResponse<User>) -> Unit){
        viewModelScope.launch {
            onResponse(repository.getUser("token"))
        }
    }
    fun changePass(data:UserVm,onResponse : (response : ServiceResponse<User>) -> Unit){
        viewModelScope.launch {
            onResponse(repository.changePass(data,"token"))
        }
    }
    fun login(data:UserVm,onResponse : (response : ServiceResponse<UserVm>) -> Unit){
        viewModelScope.launch {
            onResponse(repository.login(data))
        }
    }
    fun register(data:UserVm,onResponse : (response : ServiceResponse<User>) -> Unit){
        viewModelScope.launch {
            onResponse(repository.register(data))
        }
    }
    fun update(data:UserVm,onResponse : (response : ServiceResponse<User>) -> Unit){
        viewModelScope.launch {
            onResponse(repository.update(data,"token"))
        }
    }
}