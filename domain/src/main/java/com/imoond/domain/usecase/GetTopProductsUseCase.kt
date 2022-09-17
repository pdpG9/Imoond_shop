package com.imoond.domain.usecase

import com.imoond.domain.repository.EventListener
import com.imoond.domain.repository.ProductRepository

class GetTopProductsUseCase(private val repository: ProductRepository) {
    suspend fun execute(eventListener: EventListener) = repository.getTopProducts(eventListener)
}