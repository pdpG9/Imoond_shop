package com.imoond.data.repository.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.imoond.data.repository.room.dao.CategoryDao
import com.imoond.data.repository.room.dao.ProductDao
import com.imoond.data.repository.room.entity.CategoryEntityRoom
import com.imoond.data.repository.room.entity.ProductEntityRoom


@Database(entities = [ProductEntityRoom::class, CategoryEntityRoom::class], version = 1)
@TypeConverters(CustomTypeConverter::class)

abstract class RoomAppData : RoomDatabase() {

    abstract fun getProductDao(): ProductDao
    abstract fun getCategoryDao(): CategoryDao?

    companion object {
        private var INSTANCE: RoomAppData? = null

        fun getAppDatabase(context: Context): RoomAppData? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<RoomAppData>(
                    context.applicationContext, RoomAppData::class.java, "AppDB"
                ).build()

            }
            return INSTANCE
        }
    }
}