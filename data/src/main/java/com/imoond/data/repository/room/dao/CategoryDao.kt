package com.imoond.data.repository.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.imoond.data.repository.room.entity.CategoryEntity

@Dao
interface CategoryDao:BaseDao<CategoryEntity> {
    @Query("select * from CategoryEntity")
    suspend fun getCategories(): List<CategoryEntity>

    @Query("select * from CategoryEntity where id=:id")
    suspend fun getCategoryById(id: Int): CategoryEntity

    @Query("delete from CategoryEntity")
    override suspend fun deleteTable()
}