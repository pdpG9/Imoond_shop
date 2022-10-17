package com.imoond.domain.usecase.local

import com.imoond.domain.repository.EventListener
import com.imoond.domain.repository.LocalRepository

class GetProductsFromBasketUseCase(private val repository: LocalRepository) {
     fun execute(eventListener: EventListener<List<Int>>) = repository.getProductsFromCard(eventListener = eventListener)
}