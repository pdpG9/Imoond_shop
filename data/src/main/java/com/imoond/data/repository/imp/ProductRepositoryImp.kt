package com.imoond.data.repository.imp

import android.util.Log
import com.imoond.data.models.productModels.ProductModel
import com.imoond.data.models.productModels.ProductModelItem
import com.imoond.data.repository.network.ProductApi
import com.imoond.data.repository.room.maps.ProductMapper
import com.imoond.data.untils.Constants
import com.imoond.domain.model.ProductEntity
import com.imoond.domain.repository.EventListener
import com.imoond.domain.repository.ProductRepository
import retrofit2.Response

class ProductRepositoryImp(private val productApi: ProductApi) : ProductRepository {
    override suspend fun getProductList(eventListener: EventListener<List<ProductEntity>>) {
        try {
            eventListener.load(true)
            val response = getAllProductsFromNetwork()
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
            val response = getProductById(productId)
            if (response.isSuccessful) {
                if (response.body() == null) {
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
            val response = getAllProductsFromNetwork()
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
                    if (data.isNotEmpty()) {
                        eventListener.success(data)
                    } else {
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
            val response = getAllProductsFromNetwork()
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
            val response = getAllProductsFromNetwork()
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
            val response = getAllProductsFromNetwork()
            if (response.isSuccessful) {
                if (response.body().isNullOrEmpty()) {
                    eventListener.empty()
                    eventListener.load(false)
                    return
                } else {
                    Log.d("TAG", "getProductsByCategory: ${response.message()}")
                    val list = response.body()!!.filter {
                        Log.d(
                            "TAG",
                            "it.categories name: ${it.categories[0].name} & category name:$categoryName "
                        )
                        it.categories[0].name == categoryName
                    }
                    val data = ProductMapper().mapListEntity(list)
                    eventListener.success(data)
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

    override suspend fun getProductsByPage(
        page: Int,
        eventListener: EventListener<List<ProductEntity>>
    ) {
        eventListener.load(true)
        try {
            val response = getAllProductsFromNetwork(page)
            Log.d("TAG", "getProductsByPage: ${response.message()}")
            if (response.isSuccessful) {
                val data = response.body()?.let { ProductMapper().mapListEntity(it) }
                    Log.d("TAG", "getProductsByPage: ${data?.size}")
                if (data != null) {
                    Log.d("TAG", "eventListener.success(data) ")
                    eventListener.success(data)
                }
                eventListener.load(false)
            } else {
                eventListener.empty()
                eventListener.load(false)
            }
        } catch (e: Exception) {
            eventListener.load(false)
            eventListener.error(e.message.toString())
        }

    }

    private suspend fun getAllProductsFromNetwork(): Response<ProductModel> {
        return productApi.getAll(
            consumer_secret = Constants.CONSUMER_SECRET,
            consumer_key = Constants.CONSUMER_KEY
        )
    }

    private suspend fun getProductById(id: Int): Response<ProductModelItem> {
        return productApi.getProductById(
            id = id,
            consumer_secret = Constants.CONSUMER_SECRET,
            consumer_key = Constants.CONSUMER_KEY
        )
    }

    private suspend fun getAllProductsFromNetwork(
        page: Int
    ): Response<List<ProductModelItem>> {
        return productApi.getProducts(
            consumer_secret = Constants.CONSUMER_SECRET,
            consumer_key = Constants.CONSUMER_KEY,
            page = page
        )
    }

    companion object {
        const val PAGE_SIZE = 20
    }

}