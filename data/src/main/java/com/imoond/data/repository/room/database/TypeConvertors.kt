package com.imoond.data.repository.room.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.imoond.data.repository.room.entity.CategoryEntity
import com.imoond.data.repository.room.entity.ProductEntity

class ProductTypeConverter {
    @TypeConverter
    fun listToJson(value:List<ProductEntity>?) = Gson().toJson(value)

    @TypeConverter
    fun gsonToList(value:String?) = Gson().fromJson(value,Array<ProductEntity>::class.java).toList()
}
class CategoryTypeConverter {
    @TypeConverter
    fun listToJson(value:List<CategoryEntity>?) = Gson().toJson(value)

    @TypeConverter
    fun gsonToList(value:String?) = Gson().fromJson(value,Array<CategoryEntity>::class.java).toList()
}

class CustomTypeConverter<T>(){

    @TypeConverter
    fun fromString(value: String?):ArrayList<T>{

        val listType = object : TypeToken<ArrayList<T>>(){}.type
        return Gson().fromJson(value,listType)
    }
    @TypeConverter
    fun fromArrayList(list:ArrayList<T>?):String{
        return Gson().toJson(list)
    }
}