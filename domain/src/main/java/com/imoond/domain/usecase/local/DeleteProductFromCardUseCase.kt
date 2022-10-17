package com.imoond.domain.usecase.local

import com.imoond.domain.model.ProductEntity
import com.imoond.domain.repository.LocalRepository

class DeleteProductFromCardUseCase(private val repository: LocalRepository) {
    suspend fun execute(productEntity: ProductEntity) =
        repository.deleteProductToCard(productEntity)
}