package com.example.shopproject.model.product

data class Product(
    var id: Long? = null,
    var addDate: String?= null,
    var category: ProductCategory?= null,
    var colors: List<ProductColor>?= null,
    var description: String?= null,
    var image: String?= null,
    var price: Long?= null,
    var sizes: List<ProductSize>?= null,
    var title: String?= null,
    var visitCount: Int?= null
)