package com.example.shopproject.api.invoice

import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.invoice.Invoice
import com.example.shopproject.model.product.ProductColor
import retrofit2.http.*

interface InvoiceApi {
    @POST("/api/invoice")
    suspend fun addInvoice(
        @Body data: Invoice,
        @Header("Autorization") token: String
    ) : ServiceResponse<Invoice>

    @GET("/api/invoice/{id}")
    suspend fun getInvoiceById(
        @Path("id") id: Long,
        @Header("Autorization") token: String
    ): ServiceResponse<Invoice>

    @GET("/api/invoice/user/{userId}")
    suspend fun getInvoiceByUserId(
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int,
        @Path("userId") userId: Long,
        @Header("Autorization") token: String
    ): ServiceResponse<Invoice>
}