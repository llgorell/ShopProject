package com.example.shopproject.model.invoice

import com.example.shopproject.model.customer.User

data class Invoice(
    var id: Long?,
    var addDate: String?,
    var items: List<InvoiceItem>?,
    var paymentDate: String?,
    var status: String?,
    var transactions: List<Transaction>?,
    var user: User?
)