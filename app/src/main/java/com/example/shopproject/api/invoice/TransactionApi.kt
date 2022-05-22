package com.example.shopproject.api.invoice

import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.invoice.PaymentTransaction
import retrofit2.http.Body
import retrofit2.http.POST

interface TransactionApi {
    @POST("/api/trx/gotoPayment")
    suspend fun goToPayment(
        @Body data: PaymentTransaction
    ) : ServiceResponse<String>
}