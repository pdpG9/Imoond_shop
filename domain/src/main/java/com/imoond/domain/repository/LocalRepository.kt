package com.imoond.domain.repository

import com.imoond.domain.model.ProductEntity

interface LocalRepository {
    fun getProductsFromCard(eventListener: EventListener<List<Int>>)
    suspend fun addProductToCard(productEntity: ProductEntity):Boolean
    suspend fun deleteProductToCard(productEntity: ProductEntity):Boolean
}