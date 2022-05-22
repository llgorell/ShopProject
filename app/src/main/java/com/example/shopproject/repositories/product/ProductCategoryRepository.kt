package com.example.shopproject.repositories.product

import com.example.shopproject.api.product.ProductCategoryApi
import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.product.ProductCategory
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ProductCategoryRepository @Inject constructor(private val api: ProductCategoryApi) {
    suspend fun getCategory(): ServiceResponse<ProductCategory> {
        return try {
            api.getCategory()
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
    suspend fun getCategoryById(id : Long): ServiceResponse<ProductCategory> {
        return try {
            api.getCategoryById(id)
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
}