package com.example.shopproject.model.customer

import com.example.shopproject.db.models.UserEntity

data class UserVm(
var address : String?="",
var custumerId: Long? = null,
var firstName: String?="",
var id: Long? = null,
var lastName: String?="",
var oldPassword: String? = null,
var password: String?,
var phone: String?="",
var postalCode: String?="",
var repeatpassword: String? = null,
var token: String? = null,
var username: String?
){
    fun convertToEntity(): UserEntity {
        return UserEntity(
            userId = id!!,
            address = address,
            custumerId = custumerId!!,
            firstName = firstName,
            lastName = lastName,
            phone = phone,
            postalCode = postalCode,
            token = token,
            username = username
        )
    }
}
