package com.imoond.data.repository.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.imoond.data.repository.room.database.ProductTypeConverter

@Entity(tableName = "products")
data class ProductEntityRoom(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description: String,
    val short_description: String,
    val price: String,
    val regular_price: String,
    val sale_price: String,
    val weight: String,
    val length: String,
    val width: String,
    val height: String,
    @TypeConverters(ProductTypeConverter::class)
    val images: List<String> = listOf("https://cdn4.iconfinder.com/data/icons/refresh_cl/256/System/Box_Empty.png"),
    val category: String

)
