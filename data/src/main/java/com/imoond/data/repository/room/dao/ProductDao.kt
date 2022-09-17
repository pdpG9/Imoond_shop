package com.imoond.data.repository.room.dao

import androidx.room.*
import com.imoond.data.repository.room.entity.ProductEntity

@Dao
interface ProductDao : BaseDao<ProductEntity> {
    @Query("select * from ProductEntity")
    suspend fun getProducts(): List<ProductEntity>

    @Query("select * from ProductEntity where id=:id")
    suspend fun getProductById(id: Int): ProductEntity

    @Query("select * from ProductEntity where category=:category")
    suspend fun getProductsByCategory(category: String): List<ProductEntity>

    @Query("delete from ProductEntity")
    override suspend fun deleteTable()
}