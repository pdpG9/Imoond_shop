package com.imoond.data.repository.room.maps

import Dimensions
import com.google.gson.Gson
import com.imoond.data.models.productModels.ProductModelItem
import com.imoond.data.repository.room.entity.ProductEntityRoom
import com.imoond.domain.model.ProductEntity

class ProductMapper : Mapper<ProductModelItem, ProductEntity> {
    override fun mapData(entity: ProductEntity): ProductModelItem {
        return ProductModelItem(
            null,
            null,
            "",
            false,
            "",
            false,
            "",
            "",
            listOf(),
            null,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            null,
            entity.description,
            Dimensions(entity.height, entity.length, entity.width),
            0,
            0,
            false,
            listOf(),
            "",
            false,
            null,
            entity.id,
            listOf(),
            false,
            0,
            null,
            entity.name,
            false,
            0,
            "",
            entity.price,
            "",
            false,
            "",
            0,
            entity.regular_price,
            null,
            false,
            entity.sale_price,
            "",
            0,
            false,
            false,
            "",
            "",
            "",
            false,
            "",
            false,
            "",
            null,
            "",
            "",
            0,
            "",
            null,
            null,
            false,
            entity.weight
        )
    }

    override fun mapEntity(data: ProductModelItem): ProductEntity {
        return ProductEntity(
            data.id,
            data.name,
            data.description,
            data.short_description,
            data.price,
            data.regular_price,
            data.sale_price,
            data.weight,
            data.dimensions!!.length,
            data.dimensions.width,
            data.dimensions.height,
            data.images.map {
                it.src
            },
            data.categories[0].name
        )
    }

    fun mapDataModule(data: ProductEntity): ProductEntityRoom {
        return ProductEntityRoom(
            data.id,
            data.name,
            data.description,
            data.short_description,
            data.price,
            data.regular_price,
            data.sale_price,
            data.weight,
            data.length,
            data.width,
            data.height,
            data.images,
            data.category
        )
    }

    fun mapDataModule(list: List<ProductEntity>): List<ProductEntityRoom> {
        return list.map {
            mapDataModule(it)
        }
    }

    private fun mapDataEntity(data: ProductEntityRoom): ProductEntity {
        return ProductEntity(
            data.id,
            data.name,
            data.description,
            data.short_description,
            data.price,
            data.regular_price,
            data.sale_price,
            data.weight,
            data.length,
            data.width,
            data.height,
            data.images,
            data.category
        )
    }

    fun mapDataEntity(list: List<ProductEntityRoom>): List<ProductEntity> {
        return list.map {
            mapDataEntity(it)
        }
    }

    fun mapToString(list: List<Int>): String {
        if (list.isNullOrEmpty()) return ""
        return list.map {
          it.toString()
        }.toTypedArray().contentToString()
    }

    fun mapToList(list:String):List<Int>{
        if (list.isNullOrEmpty()) return emptyList()
        val regex = ","
        var s = list.replace("[","")
       s = s.replace("]","").trim()
      return  s.split(regex).toList().map{ it.trim().toInt() }.toList<Int>()
    }

}
