package com.imoond.data.repository.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<E> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(t:E)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(t:List<E>)

    @Update
    suspend fun update(t:E)

    @Update
    suspend fun update(t:List<E>)

    @Delete
    suspend fun delete(t:E)

    @Delete
    suspend fun delete(t:List<E>)

    suspend fun deleteTable()
}