package com.example.shopproject.model.invoice

import com.example.shopproject.model.customer.UserVm

data class PaymentTransaction(
    var items : List<InvoiceItem>?,
    var user : UserVm?
)
