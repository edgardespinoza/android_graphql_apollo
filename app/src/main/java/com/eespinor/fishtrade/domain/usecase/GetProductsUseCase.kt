package com.eespinor.fishtrade.domain.usecase

import com.eespinor.fishtrade.domain.model.ProductModel
import com.eespinor.fishtrade.domain.repository.ProductRepository

class GetProductsUseCase(
    private val productRepository: ProductRepository
) {

    suspend operator fun invoke(): List<ProductModel> {
        return productRepository.getProducts().sortedBy { it.name }
    }

}