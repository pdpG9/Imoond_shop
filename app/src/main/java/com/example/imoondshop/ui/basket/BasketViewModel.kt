package com.example.imoondshop.ui.basket

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imoond.domain.model.ProductEntity
import com.imoond.domain.repository.EventListener
import com.imoond.domain.repository.LocalRepository
import com.imoond.domain.usecase.product.GetProductByIdUseCase
import com.imoond.domain.usecase.product.GetProductsByCategory

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
    val message:LiveData<String> = _message
    private val _isShowProgress = MutableLiveData<Boolean>()
    val isShowProgress:LiveData<Boolean> = _isShowProgress
    private val _productIdsCard = MutableLiveData<List<Int>>()
    val productIdsCard: LiveData<List<Int>> = _productIdsCard
    fun loadData() {
        _isShowProgress.postValue(true)
        repository.getProductsFromCard(object : EventListener<List<Int>> {
            override fun success(data: List<Int>) {
                _productIdsCard.postValue(data)
                _message.postValue("")
                _isShowProgress.postValue(false)
            }

            override fun error(message: String) {
                _message.postValue(message)
                _isShowProgress.postValue(false)
            }

            override fun empty() {
                _message.postValue("empty")
                _isShowProgress.postValue(false)
            }

            override fun load(l: Boolean) {
                _isShowProgress.postValue(l)
            }

        })
    }

    suspend fun loadProducts(list: List<Int>) {
        _isShowProgress.postValue(true)
        val temp = mutableListOf<ProductEntity>()
        list.forEach {
            getProductByIdUseCase.execute(it, object : EventListener<ProductEntity> {
                override fun success(data: ProductEntity) {
                    temp.add(data)
                    _message.postValue("")
                    _isShowProgress.postValue(false)
                }

                override fun error(message: String) {
                    _message.postValue(message)
                    _isShowProgress.postValue(false)
                }

                override fun empty() {
                    _message.postValue("empty")
                    _isShowProgress.postValue(false)
                }

                override fun load(l: Boolean) {
                    _isShowProgress.postValue(l)
                }

            })

        }
        _products.postValue(temp)
    }

     fun loadRecommendedProducts() {
      //  _isShowProgress.postValue(true)
        _message.postValue("")
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