package com.example.imoondshop.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imoond.domain.model.CategoryEntity
import com.imoond.domain.model.ProductEntity
import com.imoond.domain.repository.EventListener
import com.imoond.domain.usecase.GetProductsByCategory

class ProductListViewModel(private val getProductsByCategory: GetProductsByCategory) : ViewModel() {
    private val _productsLive = MutableLiveData<List<ProductEntity>>()
    val productLive: LiveData<List<ProductEntity>> = _productsLive
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    suspend fun loadData(categoryName: String) {
        getProductsByCategory.execute(object : EventListener<List<ProductEntity>> {
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

            }


        }, categoryName)
    }
}