package com.example.shopproject.model.customer

import com.example.shopproject.model.customer.Customer

data class User(
    var id: Long?,
    var customer: Customer?,
    var password: String?,
    var userName: String?
)