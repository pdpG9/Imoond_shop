package com.example.imoondshop.ui.basket

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imoond.domain.model.CategoryEntity
import com.imoond.domain.model.ProductEntity
import com.imoond.domain.repository.EventListener
import com.imoond.domain.repository.LocalRepository
import com.imoond.domain.usecase.GetProductByIdUseCase
import com.imoond.domain.usecase.GetProductsByCategory
import kotlinx.coroutines.launch

class BasketViewModel(
    private val repository: LocalRepository,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val getProductsByCategory: GetProductsByCategory
) : ViewModel() {
    private val _products = MutableLiveData<List<ProductEntity>>()
    val products: LiveData<List<ProductEntity>> = _products
    private val _btAllCheck = MutableLiveData<Boolean>(false)
    val btAllCheck: LiveData<Boolean> = _btAllCheck
    private val _message = MutableLiveData<String>()
    private val _productIdsCard = MutableLiveData<List<Int>>()
    val productIdsCard: LiveData<List<Int>> = _productIdsCard
    fun loadData() {
        repository.getProductsFromCard(object : EventListener<List<Int>> {
            override fun success(data: List<Int>) {
                _productIdsCard.postValue(data)
            }

            override fun error(message: String) {
                _message.postValue(message)
            }

            override fun empty() {
                _message.postValue("empty")
            }

            override fun load(l: Boolean) {
                _message.postValue("load: $l")
            }

        })
    }

    suspend fun loadProducts(list: List<Int>) {
        val temp = mutableListOf<ProductEntity>()
        list.forEach {
            getProductByIdUseCase.execute(it, object : EventListener<ProductEntity> {
                override fun success(data: ProductEntity) {
                    temp.add(data)
                }

                override fun error(message: String) {

                }

                override fun empty() {

                }

                override fun load(l: Boolean) {

                }

            })

        }
        _products.postValue(temp)
    }

    suspend fun loadRecommendedProducts() {
        val list = products.value
        val t = mutableListOf<ProductEntity>()
        Log.d("TAG", "loadRecommendedProducts:old size ${list?.size}")
        list?.forEach {
            t.add(it)
            list.filter { k ->
                k.category != it.category
            }
        }
        Log.d("TAG", "loadRecommendedProducts:new size ${t.size}")

    }


    fun clickAllBtCheck() {
        _btAllCheck.value = !_btAllCheck.value!!
    }

}