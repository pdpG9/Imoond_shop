package com.imoond.domain.usecase

import com.imoond.domain.repository.CategoryRepository
import com.imoond.domain.repository.EventListener

class GetCategoryUseCase(private val repository: CategoryRepository) {
    suspend fun execute(eventListener: EventListener) = repository.getCategories(eventListener)
}