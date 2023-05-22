package com.eespinor.fishtrade.data

import com.apollographql.apollo3.ApolloClient
import com.eespinor.apollo.ProductQuery
import com.eespinor.apollo.ProductsQuery
import com.eespinor.fishtrade.data.mapper.toProductDetailModel
import com.eespinor.fishtrade.data.mapper.toProductModel
import com.eespinor.fishtrade.domain.model.ProductDetailModel
import com.eespinor.fishtrade.domain.model.ProductModel
import com.eespinor.fishtrade.domain.repository.ProductRepository

class ApolloProductRepository (
    private val apolloClient: ApolloClient
        ): ProductRepository{
    override suspend fun getProducts(): List<ProductModel> {
        return apolloClient.query(ProductsQuery())
            .execute()
            .data
            ?.productCollection
            ?.edges
            ?.map { it.node.toProductModel() }
            ?: emptyList()


    }

    override suspend fun getProduct(code: String): ProductDetailModel? {
        return apolloClient.query(ProductQuery(code))
            .execute()
            .data
            ?.node
            ?.onProduct?.toProductDetailModel()
    }
}