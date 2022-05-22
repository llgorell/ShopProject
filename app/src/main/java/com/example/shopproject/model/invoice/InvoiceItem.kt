package com.example.shopproject.model.invoice

import com.example.shopproject.db.models.BasketEntity
import com.example.shopproject.model.product.Product

data class InvoiceItem(
    var id: Long?,
    var product: Product?,
    var quantity: Int?,
    var unitPrice: Long?
){
    companion object{
        fun convertFromBasket(basketEntity: BasketEntity): InvoiceItem {
            return InvoiceItem(id = null,
            product = Product(basketEntity.productId),
            quantity = basketEntity.quantity,
            unitPrice = basketEntity.price
            )
        }
    }
}
