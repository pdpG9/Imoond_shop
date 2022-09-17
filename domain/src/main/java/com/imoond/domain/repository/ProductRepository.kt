package com.imoond.domain.repository

interface ProductRepository {
    suspend fun getProductList(eventListener: EventListener)
    suspend fun getProductById(productId: Int, eventListener: EventListener)
    suspend fun getProductByName(name: String, eventListener: EventListener)
    suspend fun getTopProducts(eventListener: EventListener)
    suspend fun getRecommended(eventListener: EventListener)
    suspend fun getProductByCategory(eventListener: EventListener)
}