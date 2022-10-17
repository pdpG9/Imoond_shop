package com.imoond.domain.usecase.product

import com.imoond.domain.model.ProductEntity
import com.imoond.domain.repository.EventListener
import com.imoond.domain.repository.ProductRepository

class GetProductByNameUseCase(private val repository: ProductRepository) {
    suspend fun execute(name:String, eventListener: EventListener<List<ProductEntity>>) = repository.getProductsByName(name,eventListener)
}