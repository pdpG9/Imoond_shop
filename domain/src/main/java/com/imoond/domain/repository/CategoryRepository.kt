package com.imoond.domain.repository

import com.imoond.domain.model.CategoryEntity

interface CategoryRepository {
    suspend fun getCategories(eventListener: EventListener<List<CategoryEntity>>)
    suspend fun getCategoryByName(name:String,eventListener: EventListener<List<CategoryEntity>>)
}