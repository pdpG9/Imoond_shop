package com.example.imoondshop.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imoond.domain.model.ProductEntity
import com.imoond.domain.repository.EventListener
import com.imoond.domain.usecase.GetProductByNameUseCase

class SearchViewModel(private val getProductByNameUseCase: GetProductByNameUseCase) : ViewModel() {
    private val _products = MutableLiveData<List<ProductEntity>>()
    val products:LiveData<List<ProductEntity>> = _products
    private val _message = MutableLiveData<String>()
    val message:LiveData<String> = _message
    private val _isShowProgress = MutableLiveData<Boolean>()
    val isShowProgress = _isShowProgress

    suspend fun getProductsByName(name:String){
        getProductByNameUseCase.execute(name,object :EventListener<List<ProductEntity>>{
            override fun success(data: List<ProductEntity>) {
                _products.postValue(data)
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
    }
}