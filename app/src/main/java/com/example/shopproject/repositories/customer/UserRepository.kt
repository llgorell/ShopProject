package com.example.shopproject.repositories.customer

import com.example.shopproject.api.customer.UserApi
import com.example.shopproject.api.invoice.InvoiceApi
import com.example.shopproject.api.product.ColorApi
import com.example.shopproject.api.site.SliderApi
import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.customer.User
import com.example.shopproject.model.customer.UserVm
import com.example.shopproject.model.invoice.Invoice
import com.example.shopproject.model.product.ProductColor
import com.example.shopproject.model.site.Slider
import com.example.shopproject.repositories.base.BaseRepository
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class UserRepository @Inject constructor(private val api: UserApi) : BaseRepository() {


    suspend fun getUser(token : String): ServiceResponse<User> {
        return try {
            api.getUser(prapairToken(token))
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
    suspend fun changePass(uservm : UserVm,token : String): ServiceResponse<User> {
        return try {
            api.changePass(prapairToken(token),uservm)
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
    suspend fun login(uservm : UserVm): ServiceResponse<UserVm> {
        return try {
            api.login(uservm)
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
    suspend fun register(uservm : UserVm): ServiceResponse<User> {
        return try {
            api.register(uservm)
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
    suspend fun update(uservm : UserVm,token:String): ServiceResponse<User> {
        return try {
            api.update(prapairToken(token),uservm)
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
}