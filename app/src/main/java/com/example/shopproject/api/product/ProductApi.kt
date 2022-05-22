package com.example.shopproject.api.product

import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.product.Product
import com.example.shopproject.model.product.ProductCategory
import com.example.shopproject.model.product.ProductColor
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("/api/product")
    suspend fun getProduct(
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int
    ): ServiceResponse<Product>

    @GET("/api/product/{id}")
    suspend fun getProductById(@Path("id") id: Long): ServiceResponse<Product>

    @GET("/api/product/new")
    suspend fun getNewProduct(): ServiceResponse<Product>

    @GET("/api/product/popular")
    suspend fun getPopularProduct(): ServiceResponse<Product>

    @GET("/api/product/cat/{id}")
    suspend fun getProductByCategoryId(
        @Path("id") id: Long,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int
    ): ServiceResponse<Product>
}