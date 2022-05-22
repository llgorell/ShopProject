package com.example.shopproject.viewmodels.invoice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.invoice.Invoice
import com.example.shopproject.model.invoice.PaymentTransaction
import com.example.shopproject.model.invoice.Transaction
import com.example.shopproject.model.product.ProductColor
import com.example.shopproject.repositories.invoice.InvoiceRepository
import com.example.shopproject.repositories.invoice.TransactionRepository
import com.example.shopproject.repositories.product.ColorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(private val repository : TransactionRepository) : ViewModel() {


    fun goToPayment(data:PaymentTransaction,onResponse : (response : ServiceResponse<String>) -> Unit){
        viewModelScope.launch {
            onResponse(repository.goToPayment(data))
        }
    }
}