package com.eespinor.fishtrade.data.mapper

import com.eespinor.apollo.ProductQuery
import com.eespinor.apollo.ProductsQuery
import com.eespinor.fishtrade.domain.model.ProductDetailModel
import com.eespinor.fishtrade.domain.model.ProductModel

fun ProductsQuery.Node.toProductModel(): ProductModel {
     return ProductModel(
        id = id as String,
        name = name ?: "",
        image = image ?: "",
        price = price ?: 0.0,
        stock = ((stock ?:"0") as String).toInt(),
        nodeId = nodeId
    )
}

fun ProductQuery.OnProduct.toProductDetailModel(): ProductDetailModel {
    return ProductDetailModel(
        id = id as String,
        name = name ?: "",
        image = image ?: "",
        price = price ?: 0.0,
        stock = ((stock ?:"0") as String).toInt(),
        nodeId = nodeId,
        user = (user ?: "").toString()
    )
}