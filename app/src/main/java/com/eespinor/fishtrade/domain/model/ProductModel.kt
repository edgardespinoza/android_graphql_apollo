package com.eespinor.fishtrade.domain.model

data class ProductModel (
val id: String, val name:String, val image:String, val price:Double, val stock: Int, val nodeId:String
)

data class ProductDetailModel (
    val id: String, val name:String, val image:String, val price:Double, val stock: Int,  val nodeId:String, val user:String
)
