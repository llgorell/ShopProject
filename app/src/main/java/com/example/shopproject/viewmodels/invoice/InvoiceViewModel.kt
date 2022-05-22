package com.example.shopproject.viewmodels.invoice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.invoice.Invoice
import com.example.shopproject.model.product.ProductColor
import com.example.shopproject.repositories.invoice.InvoiceRepository
import com.example.shopproject.repositories.product.ColorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoiceViewModel @Inject constructor(private val repository : InvoiceRepository) : ViewModel() {

    //TODO fix Token
    fun getInvoiceById(id:Long,onResponse : (response : ServiceResponse<Invoice>) -> Unit){
        viewModelScope.launch {
            onResponse(repository.getInvoiceById(id,"token"))
        }
    }

    fun getInvoiceByUserId(pageIndex : Int,pageSize: Int,userId : Long,onResponse :(response : ServiceResponse<Invoice>) -> Unit){
        viewModelScope.launch {
            onResponse(repository.getInvoiceByUserId(pageIndex,pageSize,userId,"token"))
        }
    }
    fun addInvoice(data:Invoice,onResponse : (response : ServiceResponse<Invoice>) -> Unit){
        viewModelScope.launch {
            onResponse(repository.addInvoice(data,"token"))
        }
    }
}