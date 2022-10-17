package com.imoond.domain.usecase.category

import com.imoond.domain.model.CategoryEntity
import com.imoond.domain.repository.CategoryRepository
import com.imoond.domain.repository.EventListener

class GetCategoryUseCase(private val repository: CategoryRepository) {
    suspend fun execute(eventListener: EventListener<List<CategoryEntity>>) = repository.getCategories(eventListener)
}