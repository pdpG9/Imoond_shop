package com.example.imoondshop.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.imoond.domain.model.CategoryEntity
import com.imoond.domain.model.ProductEntity
import com.imoond.domain.repository.EventListener
import com.imoond.domain.usecase.GetCategoryUseCase
import com.imoond.domain.usecase.GetProductListUseCase

class HomeViewModel(
    private val getProductListUseCase: GetProductListUseCase,
    private val getCategoryUseCase: GetCategoryUseCase
) : ViewModel() {
    private val _productsLive = MutableLiveData<List<ProductEntity>>()
    val productLive: LiveData<List<ProductEntity>> = _productsLive
    private val _isShowProgress = MutableLiveData<Boolean>()
    val isShowProgress: LiveData<Boolean> = _isShowProgress
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message
    private val _categoryLive = MutableLiveData<List<CategoryEntity>>()
    val categoryLive: LiveData<List<CategoryEntity>> = _categoryLive
    private val _isClickBtTopProd = MutableLiveData<Boolean>()
    val isClickBtTopProd: LiveData<Boolean> = _isClickBtTopProd

    suspend fun loadData() {
        Log.d("TAG", "loadData: ")

        getProductListUseCase.execute(object : EventListener<List<ProductEntity>> {
            override fun success(data: List<ProductEntity>) {
                _productsLive.postValue(data)
            }

            override fun error(message: String) {
                _message.postValue(message)
            }

            override fun empty() {
                _message.postValue("empty")
            }

            override fun load(l: Boolean) {
                _isShowProgress.postValue(l)
            }

        })
        getCategoryUseCase.execute(object :EventListener<List<CategoryEntity>>{
            override fun success(data: List<CategoryEntity>) {
                _categoryLive.postValue(data)
            }

            override fun error(message: String) {
                _message.postValue(message)
            }

            override fun empty() {
                _message.postValue("empty")
            }

            override fun load(l: Boolean) {
                _isShowProgress.postValue(l)
            }

        })

        _isClickBtTopProd.value = true
     }

    fun clickTopProductBt() {
        _isClickBtTopProd.value = true

    }

    fun clickRecommendedProdBt() {
        _isClickBtTopProd.value = false

    }

}