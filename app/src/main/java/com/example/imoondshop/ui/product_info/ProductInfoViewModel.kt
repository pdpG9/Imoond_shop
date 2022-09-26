package com.example.imoondshop.ui.product_info

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imoond.domain.model.ProductEntity
import com.imoond.domain.repository.EventListener
import com.imoond.domain.usecase.AddProductToCardUseCase
import com.imoond.domain.usecase.GetProductByIdUseCase
import com.imoond.domain.usecase.GetProductsFromBasketUseCase

class ProductInfoViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val addProductToCardUseCase: AddProductToCardUseCase,
    private val getProductsFromBasketUseCase: GetProductsFromBasketUseCase
) : ViewModel() {
    private val _productData = MutableLiveData<ProductEntity>()
    val productData: LiveData<ProductEntity> = _productData
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message
    private val _countBasketPr = MutableLiveData<Int>()
    val countBasketPr:LiveData<Int> = _countBasketPr
    private val _isShowProgress = MutableLiveData<Boolean>()
    val isShowProgress = _isShowProgress

    fun loadData() {
        getProductsFromBasketUseCase.execute(object :EventListener<List<Int>>{
            override fun success(data: List<Int>) {
                _countBasketPr.postValue(data.size)
                Log.d("TAG", "getProductsFromBasketUseCase->success:${data.size} ")
            }

            override fun error(message: String) {
                _countBasketPr.postValue(0)
                Log.d("TAG", "error:$message ")
            }

            override fun empty() {
                _countBasketPr.postValue(0)
                Log.d("TAG", "empty: ")
            }

            override fun load(l: Boolean) {

            }

        }

         )
    }


    suspend fun getProductById(position: Int) {
        getProductByIdUseCase.execute(position, object : EventListener<ProductEntity> {
            override fun success(data: ProductEntity) {
                _productData.postValue(data)
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
    suspend fun addToCard(){
        if (productData.value!=null){
            addProductToCardUseCase.execute(productData.value!!)
            var temp = countBasketPr.value
            Log.d("TAG", "addToCard: $temp")
            if (temp!=null) temp =0
            temp = temp?.plus(1)
            _countBasketPr.postValue(temp!!)
            _message.postValue("Product successfully added to basket!")
        }else{
            _message.postValue("Product not found!")
        }
    }


}