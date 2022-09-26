package com.imoond.data.repository.imp

import android.content.SharedPreferences
import android.util.Log
import com.imoond.data.repository.room.maps.ProductMapper
import com.imoond.data.untils.Constants
import com.imoond.domain.model.ProductEntity
import com.imoond.domain.repository.EventListener
import com.imoond.domain.repository.LocalRepository

class LocalRepositoryImp(private val pref: SharedPreferences):LocalRepository {
    override  fun getProductsFromCard(eventListener: EventListener<List<Int>>) {
        try {
           val s =  pref.getString(Constants.CARD_PRODUCTS,"")!!
            Log.d("TAG", "getProductsFromCard:shared pref string ->  $s")
          val response =   ProductMapper().mapToList(s)
            if (response.isNotEmpty()){
                eventListener.success(response)
            }else{
                eventListener.error("empty")
            }

        }catch (e:Exception){
            eventListener.error(e.message.toString())
        }
    }

    override suspend fun addProductToCard(productEntity: ProductEntity): Boolean {
        return try {
            var s =  pref.getString(Constants.CARD_PRODUCTS,"")!!
            Log.d("TAG", "addProductToCard: $s")
            val response =   ProductMapper().mapToList(s)
            val temp = mutableListOf<Int>()
            temp.addAll(response)
            if (!temp.contains(productEntity.id)){
            temp.add(productEntity.id)
            }else{
                Log.d("TAG", "addProductToCard: product already have")
            }
            s = ProductMapper().mapToString(temp)
            pref.edit().putString(Constants.CARD_PRODUCTS,s).apply()
            true
        }catch (e:Exception){
            Log.e("TAG", "addProductToCard: ${e.message}")
            false
        }
    }

    override suspend fun deleteProductToCard(productEntity: ProductEntity): Boolean {
        return try {

            var s =  pref.getString(Constants.CARD_PRODUCTS,"")!!
            val response =   ProductMapper().mapToList(s)
            val temp = mutableListOf<Int>()
            temp.addAll(response)
            temp.remove(productEntity.id)
            s = ProductMapper().mapToString(temp)
            pref.edit().putString(Constants.CARD_PRODUCTS,s).apply()
            true
        }catch (e:Exception){
            Log.e("TAG", "addProductToCard: ${e.message}", )
            return false
        }
    }
}