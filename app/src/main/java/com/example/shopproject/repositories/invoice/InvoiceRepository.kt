package com.example.shopproject.repositories.invoice

import com.example.shopproject.api.invoice.InvoiceApi
import com.example.shopproject.api.product.ColorApi
import com.example.shopproject.api.site.SliderApi
import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.invoice.Invoice
import com.example.shopproject.model.product.ProductColor
import com.example.shopproject.model.site.Slider
import com.example.shopproject.repositories.base.BaseRepository
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class InvoiceRepository @Inject constructor(private val api: InvoiceApi) : BaseRepository() {


    suspend fun addInvoice(data : Invoice,token : String): ServiceResponse<Invoice> {
        return try {
            api.addInvoice(data,prapairToken(token))
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
    suspend fun getInvoiceById(id : Long,token : String): ServiceResponse<Invoice> {
        return try {
            api.getInvoiceById(id,prapairToken(token))
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
    suspend fun getInvoiceByUserId(pageIndex : Int,pageSize: Int,userId : Long,token : String): ServiceResponse<Invoice> {
        return try {
            api.getInvoiceByUserId(pageIndex,pageSize,userId,prapairToken(token))
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
}