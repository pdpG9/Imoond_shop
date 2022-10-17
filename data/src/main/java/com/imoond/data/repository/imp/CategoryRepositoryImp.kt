package com.imoond.data.repository.imp

import com.imoond.data.models.categroyModel.CategoryModel
import com.imoond.data.models.productModels.ProductModel
import com.imoond.data.repository.network.CategoryApi
import com.imoond.data.repository.room.maps.CategoryMapper
import com.imoond.data.untils.Constants
import com.imoond.domain.model.CategoryEntity
import com.imoond.domain.repository.CategoryRepository
import com.imoond.domain.repository.EventListener
import retrofit2.Response


class CategoryRepositoryImp(private val categoryApi: CategoryApi) : CategoryRepository {
    override suspend fun getCategories(eventListener: EventListener<List<CategoryEntity>>) {
        try {
            eventListener.load(true)
            val response = getAllCategoriesFromNetwork()
            if (response.isSuccessful) {
                if (!response.body().isNullOrEmpty()) {
                    val list = response.body()!!.toList()
                    val data = CategoryMapper().mapListEntity(list)
                    eventListener.success(data)
                    eventListener.load(false)
                } else {
                    eventListener.empty()
                    eventListener.load(false)
                    return
                }
            } else {
                eventListener.error(response.message())
                eventListener.load(false)
            }
        } catch (e: Exception) {
            eventListener.error(e.message.toString())
            eventListener.load(false)
        }

    }

    override suspend fun getCategoryByName(
        name: String,
        eventListener: EventListener<List<CategoryEntity>>
    ) {
        try {
            eventListener.load(true)
            val response = getAllCategoriesFromNetwork()
            if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                val list = response.body()!!.toList()
                list.filter {
                    it.name == name
                }
                val data = CategoryMapper().mapListEntity(list)
                eventListener.success(data)
                eventListener.load(false)
            } else {
                eventListener.error("response body null or empty!")
            }

        } catch (e: Exception) {
            eventListener.error(e.message.toString())
        }

    }
    private suspend fun getAllCategoriesFromNetwork(): Response<CategoryModel> {
        return categoryApi.getCategories(consumer_secret = Constants.CONSUMER_SECRET, consumer_key = Constants.CONSUMER_KEY)
    }

}
