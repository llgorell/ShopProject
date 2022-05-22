package com.example.shopproject.api.product

import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.product.ProductColor
import retrofit2.http.GET
import retrofit2.http.Path

interface ColorApi {
    @GET("/api/color")
    suspend fun getColor():ServiceResponse<ProductColor>

    @GET("/api/color/{id}")
    suspend fun getColorById(@Path("id")id:Long):ServiceResponse<ProductColor>
}