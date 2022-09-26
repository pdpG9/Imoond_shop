package com.imoond.data.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.imoond.data.repository.room.entity.ProductEntityRoom

@Dao
interface ProductDao {
    @Query("select * from products")
    fun getProducts(): LiveData<List<ProductEntityRoom>>

//    @Query("select * from ProductEntityRoom where id=:id")
//    suspend fun getProductById(id: Int): ProductEntityRoom
//
//    @Query("select * from ProductEntityRoom where category=:category")
//    suspend fun getProductsByCategory(category: String): List<ProductEntityRoom>

    @Query("delete from products")
    suspend fun deleteTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(t:ProductEntityRoom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(t:List<ProductEntityRoom>)

    @Delete
    suspend fun delete(t:ProductEntityRoom)

    @Delete
    suspend fun delete(t:List<ProductEntityRoom>)
}