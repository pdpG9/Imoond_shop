package com.imoond.domain.usecase

import com.imoond.domain.model.ProductEntity
import com.imoond.domain.repository.EventListener
import com.imoond.domain.repository.LocalRepository

class GetProductsFromBasketUseCase(private val repository: LocalRepository) {
     fun execute(eventListener: EventListener<List<Int>>) = repository.getProductsFromCard(eventListener = eventListener)
}