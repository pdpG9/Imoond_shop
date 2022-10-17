package com.imoond.domain.usecase.product

import com.imoond.domain.model.ProductEntity
import com.imoond.domain.repository.EventListener
import com.imoond.domain.repository.ProductRepository

class GetProductByPageUseCase(private val repository: ProductRepository) {
    suspend fun execute(page:Int, eventListener: EventListener<List<ProductEntity>>) = repository.getProductsByPage(page, eventListener)
}