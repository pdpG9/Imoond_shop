package com.imoond.domain.usecase

import com.imoond.domain.repository.EventListener
import com.imoond.domain.repository.ProductRepository

class GetProductByNameUseCase(private val repository: ProductRepository) {
    suspend fun execute(name:String, eventListener: EventListener) = repository.getProductByName(name,eventListener)
}