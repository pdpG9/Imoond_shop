package com.imoond.domain.usecase.local

import com.imoond.domain.model.ProductEntity
import com.imoond.domain.repository.LocalRepository

class AddProductToCardUseCase(private val repository: LocalRepository) {
    suspend fun execute(productEntity: ProductEntity) = repository.addProductToCard(productEntity)
}