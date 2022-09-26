package com.imoond.domain.repository

import com.imoond.domain.model.CategoryEntity
import com.imoond.domain.model.ProductEntity

interface ProductRepository {
    suspend fun getProductList(eventListener: EventListener<List<ProductEntity>>)
    suspend fun getProductById(productId: Int, eventListener: EventListener<ProductEntity>)
    suspend fun getProductsByName(name: String, eventListener: EventListener<List<ProductEntity>>)
    suspend fun getTopProducts(eventListener: EventListener<List<ProductEntity>>)
    suspend fun getRecommended(eventListener: EventListener<List<ProductEntity>>)
    suspend fun getProductsByCategory(eventListener: EventListener<List<ProductEntity>>,categoryName: String)
}