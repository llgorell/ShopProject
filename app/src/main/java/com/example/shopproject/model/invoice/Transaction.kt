package com.example.shopproject.model.invoice

data class Transaction(
    var id: Long?,
    var amount: Long?,
    var cardHolder: String?,
    var code: Int?,
    var custom: String?,
    var customerPhone: String?,
    var orderId: String?,
    var refId: String?,
    var refundRequest: String,
    var shaparakRefId: String?,
    var transId: String?
)