package com.example.shopproject.api.customer

import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.customer.User
import com.example.shopproject.model.customer.UserVm
import com.example.shopproject.model.product.ProductColor
import retrofit2.http.*

interface UserApi {
    @GET("/api/user")
    suspend fun getUser(@Header("Autorization") token: String): ServiceResponse<User>

    @PUT("/api/user/changePass")
    suspend fun changePass(
        @Header("Autorization") token: String,
        @Body user: UserVm
    ): ServiceResponse<User>

    @POST("/api/user/login")
    suspend fun login(
        @Body user: UserVm
    ): ServiceResponse<UserVm>

    @POST("/api/user/register")
    suspend fun register(
        @Body user: UserVm
    ): ServiceResponse<User>

    @PUT("/api/user/update")
    suspend fun update(
        @Header("Autorization") token: String,
        @Body user: UserVm
    ): ServiceResponse<User>
}