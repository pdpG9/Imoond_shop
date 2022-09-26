package com.imoond.data.repository.room.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.imoond.data.repository.room.entity.CategoryEntityRoom
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList

class ProductTypeConverter {
    @TypeConverter
    fun productImagesToJson(list: List<String>): String {
        if (list.isEmpty()) {
            val list2 =
                listOf<String>("https://cdn4.iconfinder.com/data/icons/refresh_cl/256/System/Box_Empty.png")
            return Gson().toJson(list2)
        }
        return Gson().toJson(list)
    }
    @TypeConverter
    fun productGsonToImages(data:String):List<String>{
        val type =object :TypeToken<List<String>>(){}.type
        return Gson().fromJson<List<String>>(data, type)
    }

//    @TypeConverter
//    fun fromListString(list: List<String>):String{
//        return list.stream().collect(Collectors.joining(","))
//    }
//    fun toListString(data:String): MutableList<List<String>> {
//        return Arrays.asList(data.split(","))
//    }
}

class CategoryTypeConverter {

    @TypeConverter
    fun gsonToList(value: String?) =
        Gson().fromJson(value, Array<CategoryEntityRoom>::class.java).toList()
}

open class CustomTypeConverter<T>() {

    @TypeConverter
    fun fromString(value: String?): ArrayList<T> {

        val listType = object : TypeToken<ArrayList<T>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<T>?): String {
        return Gson().toJson(list)
    }
}