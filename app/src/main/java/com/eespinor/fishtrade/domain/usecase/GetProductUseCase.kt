package com.eespinor.fishtrade.domain.usecase

import com.eespinor.fishtrade.domain.model.ProductDetailModel
import com.eespinor.fishtrade.domain.repository.ProductRepository

class GetProductUseCase(
    private val productRepository: ProductRepository
) {

    suspend operator fun invoke(code: String): ProductDetailModel? {
        return productRepository.getProduct(code)
    }

}