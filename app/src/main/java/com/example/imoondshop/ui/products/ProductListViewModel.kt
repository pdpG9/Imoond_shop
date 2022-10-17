package com.example.imoondshop.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imoond.domain.model.ProductEntity
import com.imoond.domain.repository.EventListener
import com.imoond.domain.usecase.product.GetProductsByCategory

class ProductListViewModel(private val getProductsByCategory: GetProductsByCategory) : ViewModel() {
    private val _productsLive = MutableLiveData<List<ProductEntity>>()
    val productLive: LiveData<List<ProductEntity>> = _productsLive
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message
    private val _isShowProgress = MutableLiveData<Boolean>()
    val isShowProgress: LiveData<Boolean> = _isShowProgress

    suspend fun loadData(categoryName: String) {
        _isShowProgress.postValue(true)
        getProductsByCategory.execute(object : EventListener<List<ProductEntity>> {
            override fun success(data: List<ProductEntity>) {
                _productsLive.postValue(data)
                _message.postValue("")
            }

            override fun error(message: String) {
                _message.postValue(message)
            }

            override fun empty() {
                _message.postValue("empty")
            }

            override fun load(l: Boolean) {
                _message.postValue("")
                _isShowProgress.postValue(l)

            }


        }, categoryName)
    }
}