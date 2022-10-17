package com.imoond.domain.usecase.product

import com.imoond.domain.model.ProductEntity
import com.imoond.domain.repository.EventListener
import com.imoond.domain.repository.ProductRepository

class GetProductListUseCase(private val repository: ProductRepository) {
    suspend fun execute(eventListener: EventListener<List<ProductEntity>>) = repository.getProductList(eventListener)
}