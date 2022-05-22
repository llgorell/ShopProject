package com.example.shopproject.api.product

import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.product.ProductCategory
import com.example.shopproject.model.product.ProductColor
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductCategoryApi {
    @GET("/api/productCategory")
    suspend  fun getCategory():ServiceResponse<ProductCategory>

    @GET("/api/productCategory/{id}")
    suspend fun getCategoryById(@Path("id")id:Long):ServiceResponse<ProductCategory>
}