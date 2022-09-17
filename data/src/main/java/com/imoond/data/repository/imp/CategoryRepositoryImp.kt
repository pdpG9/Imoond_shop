package com.imoond.data.repository.imp

import com.imoond.data.repository.network.CategoryApi
import com.imoond.domain.repository.CategoryRepository
import com.imoond.domain.repository.Event
import com.imoond.domain.repository.EventListener
import java.lang.Exception


class CategoryRepositoryImp(private val categoryApi: CategoryApi):CategoryRepository {
    override suspend fun getCategories(eventListener: EventListener) {
        try {
                eventListener.render(Event.Loading)
            val response = categoryApi.getCategories()
            if (response.isSuccessful){
                if (!response.body().isNullOrEmpty()){
            eventListener.render(Event.Success(response.body()))
                }else{
                    eventListener.render(Event.Empty)
                    return
                }
            }else{
                eventListener.render(Event.Error(response.message()))
            }
        }catch (e:Exception){
            eventListener.render(Event.Error(e.message.toString()))
        }

    }

}