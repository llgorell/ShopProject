package com.example.shopproject.repositories.product

import com.example.shopproject.api.product.ProductApi
import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.product.Product
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class ProductRepository @Inject constructor(private val api: ProductApi) {


    suspend fun getProduct(pageIndex : Int,pageSize:Int): ServiceResponse<Product> {
        return try {
            api.getProduct(pageIndex,pageSize)
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
    suspend fun getProductById(id : Long): ServiceResponse<Product> {
        return try {
            api.getProductById(id)
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
    suspend fun getNewProduct(): ServiceResponse<Product> {
        return try {
            api.getNewProduct()
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
    suspend fun getPopularProduct(): ServiceResponse<Product> {
        return try {
            api.getPopularProduct()
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }

    suspend fun getProductByCategoryId(id : Long,pageIndex : Int,pageSize:Int): ServiceResponse<Product> {
        return try {
            api.getProductByCategoryId(id,pageIndex,pageSize)
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
}