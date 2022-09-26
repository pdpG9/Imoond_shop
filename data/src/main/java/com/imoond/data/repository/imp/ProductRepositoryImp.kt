package com.imoond.data.repository.imp

import com.imoond.data.repository.network.ProductApi
import com.imoond.data.repository.room.maps.ProductMapper
import com.imoond.domain.model.ProductEntity
import com.imoond.domain.repository.EventListener
import com.imoond.domain.repository.ProductRepository
import java.lang.Exception

class ProductRepositoryImp(private val productApi: ProductApi) : ProductRepository {
    override suspend fun getProductList(eventListener: EventListener<List<ProductEntity>>) {
        try {
            eventListener.load(true)
            val response = productApi.getAll()
            if (response.isSuccessful) {
                if (response.body().isNullOrEmpty()) {
                    eventListener.empty()
                    return
                }
                val data = ProductMapper().mapListEntity(response.body()!!.toList())
                eventListener.success(data)

            } else {
                eventListener.error(response.message())
            }
        } catch (e: Exception) {
            eventListener.error(e.message.toString())
        }
    }

    override suspend fun getProductById(
        productId: Int,
        eventListener: EventListener<ProductEntity>
    ) {
        try {
            eventListener.load(true)
            val response = productApi.getProductById(productId)
            if (response.isSuccessful) {
                if (response.body()==null) {
                    eventListener.empty()
                    eventListener.load(false)
                    return
                }
                val data = ProductMapper().mapEntity(response.body()!!)
                eventListener.success(data)
                eventListener.load(false)

            } else {
                eventListener.error(response.message())
                eventListener.load(false)
            }
        } catch (e: Exception) {
            eventListener.error(e.message.toString())
            eventListener.load(false)
        }
    }

    override suspend fun getProductsByName(
        name: String,
        eventListener: EventListener<List<ProductEntity>>
    ) {
        try {
            eventListener.load(true)
            val response = productApi.getAll()
            if (response.isSuccessful) {
                if (response.body().isNullOrEmpty()) {
                    eventListener.empty()
                    eventListener.load(false)
                    return
                } else {
                    val list = response.body()!!.filter {
                        it.name.lowercase().startsWith(name.lowercase())
                    }
                    val data = ProductMapper().mapListEntity(list)
                    if (data.isNotEmpty()){
                    eventListener.success(data)
                    }else{
                        eventListener.error("The product was not found!")
                    }
                    eventListener.load(false)
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

    override suspend fun getTopProducts(eventListener: EventListener<List<ProductEntity>>) {
        try {
            eventListener.load(true)
            val response = productApi.getAll()
            if (response.isSuccessful) {
                if (response.body().isNullOrEmpty()) {
                    eventListener.empty()
                    eventListener.load(false)
                    return
                }
                val data = ProductMapper().mapListEntity(response.body()!!.toList())
                eventListener.success(data)
                eventListener.load(false)

            } else {
                eventListener.error(response.message())
                eventListener.load(false)
            }
        } catch (e: Exception) {
            eventListener.error(e.message.toString())
            eventListener.load(false)
        }
    }

    override suspend fun getRecommended(eventListener: EventListener<List<ProductEntity>>) {
        try {
            eventListener.load(true)
            val response = productApi.getAll()
            if (response.isSuccessful) {
                if (response.body().isNullOrEmpty()) {
                    eventListener.empty()
                    return
                }
                val data = ProductMapper().mapListEntity(response.body()!!.toList())
                eventListener.success(data)

            } else {
                eventListener.error(response.message())
            }
        } catch (e: Exception) {
            eventListener.error(e.message.toString())
        }
    }

    override suspend fun getProductsByCategory(
        eventListener: EventListener<List<ProductEntity>>,
        categoryName: String
    ) {
        try {
            eventListener.load(true)
            val response = productApi.getAll()
            if (response.isSuccessful) {
                if (response.body().isNullOrEmpty()) {
                    eventListener.empty()
                    return
                } else {
                    val list = response.body()!!.filter {
                        it.categories[0].name == categoryName
                    }
                    val data = ProductMapper().mapListEntity(list)
                    eventListener.success(data)
                }
            } else {
                eventListener.error(response.message())
            }
        }catch (e: Exception) {
            eventListener.error(e.message.toString())
        }
    }

}