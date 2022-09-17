package com.imoond.domain.usecase

import com.imoond.domain.repository.EventListener
import com.imoond.domain.repository.ProductRepository

class GetProductByIdUseCase(private val repository: ProductRepository) {
    suspend fun execute(id:Int, eventListener: EventListener) = repository.getProductById(id,eventListener)
}