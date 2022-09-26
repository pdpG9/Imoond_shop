package com.imoond.data.repository.room.maps

import com.imoond.data.models.categroyModel.CategoryModelItem
import com.imoond.data.models.productModels.Image
import com.imoond.data.repository.room.entity.CategoryEntityRoom
import com.imoond.domain.model.CategoryEntity

class CategoryMapper:Mapper<CategoryModelItem, CategoryEntity>{
        override fun mapData(entity: CategoryEntity): CategoryModelItem {
                return CategoryModelItem(null,0,"","",entity.id, Image("","","","","",0,"",entity.images),0,entity.name,0,"")
        }

        override fun mapEntity(data: CategoryModelItem): CategoryEntity {
                var image = "https://imoond.com/wp-content/uploads/2022/08/photo_2022-08-24_09-50-49.jpg"
                if (data.image!=null){
                        image = data.image.src
                }
                return CategoryEntity(data.id,data.name,image)
        }

        private fun mapDataModule(data: CategoryEntity): CategoryEntityRoom {
                return CategoryEntityRoom(data.id,data.name,data.images)
        }
        fun mapDataModule(list:List<CategoryEntity>):List<CategoryEntityRoom>{
                return list.map {
                        mapDataModule(it)
                }
        }

        private fun mapDataEntity(data: CategoryEntityRoom): CategoryEntity {
                return CategoryEntity(data.id,data.name,data.images)
        }
        fun mapDataEntity(list:List<CategoryEntityRoom>):List<CategoryEntity>{
                return list.map {
                        mapDataEntity(it)
                }
        }

}