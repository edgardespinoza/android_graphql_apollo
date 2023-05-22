package com.eespinor.fishtrade.domain.repository

import com.eespinor.fishtrade.domain.model.ProductDetailModel
import com.eespinor.fishtrade.domain.model.ProductModel


interface ProductRepository {
    suspend fun getProducts():List<ProductModel>
    suspend fun getProduct(code:String):ProductDetailModel?
}