package com.imoond.data.repository.room.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.imoond.data.repository.room.dao.ProductDao
import com.imoond.data.repository.room.entity.ProductEntityRoom
import com.imoond.data.untils.Constants


@Database(entities = [ProductEntityRoom::class], version = 1, exportSchema = false)
@TypeConverters(ProductTypeConverter::class)

abstract class AppDatabase : RoomDatabase() {
    abstract fun getProductDao(): ProductDao

    companion object {

        @Volatile
        private var INSTANCE:AppDatabase?=null
        fun getDatabase(context: Context):AppDatabase{
            if (INSTANCE==null){
                val instance = Room.databaseBuilder(
                    context.applicationContext
                    ,AppDatabase::class.java
                    , Constants.DB_NAME)
                    .build()
                INSTANCE = instance
            }
            return INSTANCE as AppDatabase
        }
    }

}