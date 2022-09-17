package com.imoond.data.repository.imp

import com.imoond.data.repository.network.ProductApi
import com.imoond.domain.repository.Event
import com.imoond.domain.repository.EventListener
import com.imoond.domain.repository.ProductRepository
import java.lang.Exception

class ProductRepositoryImp(private val productApi: ProductApi) : ProductRepository {
    override suspend fun getProductList(eventListener: EventListener) {
        try {
            eventListener.render(Event.Loading)
            val response = productApi.getAll()
            if (response.isSuccessful){
                if (response.body().isNullOrEmpty()){
                    eventListener.render(Event.Empty)
                return
                }
                eventListener.render(Event.Success(response.body()))

            }else{
                eventListener.render(Event.Error(response.message()))
            }
        }catch (e:Exception){
            eventListener.render(Event.Error("${e.message}"))
        }
    }

    override suspend fun getProductById(productId: Int, eventListener: EventListener) {
        try {
            eventListener.render(Event.Loading)
            val response = productApi.getProductById(productId)
            if (response.isSuccessful){
                if (response.body()!=null){
                    eventListener.render(Event.Empty)
                    return
                }
                eventListener.render(Event.Success(response.body()))

            }else{
                eventListener.render(Event.Error(response.message()))
            }
        }catch (e:Exception){
            eventListener.render(Event.Error("${e.message}"))
        }
    }

    override suspend fun getProductByName(name: String, eventListener: EventListener) {
        try {
            eventListener.render(Event.Loading)
            val response = productApi.getAll()
            if (response.isSuccessful){
                if (response.body().isNullOrEmpty()){
                    eventListener.render(Event.Empty)
                    return
                }
                eventListener.render(Event.Success(response.body()))

            }else{
                eventListener.render(Event.Error(response.message()))
            }
        }catch (e:Exception){
            eventListener.render(Event.Error("${e.message}"))
        }
    }

    override suspend fun getTopProducts(eventListener: EventListener) {
        TODO("Not yet implemented")
    }

    override suspend fun getRecommended(eventListener: EventListener) {
        TODO("Not yet implemented")
    }

    override suspend fun getProductByCategory(eventListener: EventListener) {
        TODO("Not yet implemented")
    }

}