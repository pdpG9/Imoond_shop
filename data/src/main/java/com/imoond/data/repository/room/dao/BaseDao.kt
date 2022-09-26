package com.imoond.data.repository.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<E> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(t:E):Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(t:List<E>):Boolean

    @Update
    suspend fun update(t:E):Boolean

    @Update
    suspend fun update(t:List<E>):Boolean

    @Delete
    suspend fun delete(t:E):Boolean

    @Delete
    suspend fun delete(t:List<E>):Boolean

    suspend fun deleteTable()
}