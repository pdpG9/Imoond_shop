package com.imoond.domain.usecase

import com.imoond.domain.model.ProductEntity
import com.imoond.domain.repository.EventListener
import com.imoond.domain.repository.ProductRepository

class GetTopProductsUseCase(private val repository: ProductRepository) {
    suspend fun execute(eventListener: EventListener<List<ProductEntity>>) = repository.getTopProducts(eventListener)
}