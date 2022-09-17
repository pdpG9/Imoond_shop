package com.imoond.data.repository.room.maps

import com.imoond.data.repository.room.entity.ProductEntity
import com.imoond.data.models.productModels.ProductModelItem
import com.imoond.domain.model.productModels.Dimensions

class ProductMapper:Mapper<ProductModelItem, ProductEntity> {
    override fun mapData(entity: ProductEntity): ProductModelItem {
        return ProductModelItem(null,null,"",false,"",false,"","", listOf(),null,"",""
        ,"","","","","","",null,entity.description, Dimensions(entity.height,entity.length,entity.width),
            0,0,false, listOf(),"",false,null,entity.id, listOf(),false,0,null,entity.name,
            false,0,"",entity.price,"",false,"",0,entity.regular_price,null,false,entity.sale_price
                ,"",0,false,false,"","","",false,"",false,"",null,"","",0
        ,"",null,null,false,entity.weight
        )
    }

    override fun mapEntity(data: ProductModelItem): ProductEntity {
        return ProductEntity(data.id,data.name,data.description,data.short_description,data.price,data.regular_price,data.sale_price,data.weight,data.dimensions!!.length,data.dimensions.width,data.dimensions.height,data.images.map {
            it.src
        },data.categories.get(0).name)
    }
}