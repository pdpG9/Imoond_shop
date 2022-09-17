package com.example.imoondshop.ui.product_info

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imoond.domain.model.productModels.ProductEntitiyItem
import com.imoond.domain.usecase.GetProductByIdUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ProductInfoViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val scope: LifecycleCoroutineScope
) : ViewModel() {
    private val _listImage = MutableLiveData<List<String>>()
    val listImage: LiveData<List<String>> = _listImage
    private val _productData = MutableLiveData<ProductEntitiyItem>()
    val productData: LiveData<ProductEntitiyItem> = _productData

    fun getProductById(position: Int) {
        getProductByIdUseCase.execute(position).onEach {
            _productData.value = it
        }.launchIn(scope)
    }


}