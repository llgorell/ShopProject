package com.example.shopproject.repositories.invoice

import com.example.shopproject.api.invoice.InvoiceApi
import com.example.shopproject.api.invoice.TransactionApi
import com.example.shopproject.api.product.ColorApi
import com.example.shopproject.api.site.SliderApi
import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.invoice.Invoice
import com.example.shopproject.model.invoice.PaymentTransaction
import com.example.shopproject.model.invoice.Transaction
import com.example.shopproject.model.product.ProductColor
import com.example.shopproject.model.site.Slider
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class TransactionRepository @Inject constructor(private val api: TransactionApi) {


    suspend fun goToPayment(data : PaymentTransaction): ServiceResponse<String> {
        return try {
            api.goToPayment(data)
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
}