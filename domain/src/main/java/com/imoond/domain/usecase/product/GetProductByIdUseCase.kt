package com.imoond.domain.usecase.product

import com.imoond.domain.model.ProductEntity
import com.imoond.domain.repository.EventListener
import com.imoond.domain.repository.ProductRepository

class GetProductByIdUseCase(private val repository: ProductRepository) {
    suspend fun execute(id:Int, eventListener: EventListener<ProductEntity>) = repository.getProductById(id,eventListener)
}