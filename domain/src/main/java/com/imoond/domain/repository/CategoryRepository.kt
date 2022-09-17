package com.imoond.domain.repository

interface CategoryRepository {
    suspend fun getCategories(eventListener: EventListener)
}