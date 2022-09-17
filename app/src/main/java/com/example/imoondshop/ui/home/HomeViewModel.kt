package com.example.imoondshop.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.imoond.domain.model.CategoryModel
import com.imoond.domain.model.ProductModel
import com.imoond.domain.usecase.GetCategoryUseCase
import com.imoond.domain.usecase.GetProductListUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(
    private val getProductListUseCase: GetProductListUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val scope: LifecycleCoroutineScope
) : ViewModel() {
    private val _productsLive = MutableLiveData<List<ProductModel>>()
    val productLive: LiveData<List<ProductModel>> = _productsLive
    private val _categoryLive = MutableLiveData<List<CategoryModel>>()
    val categoryLive: LiveData<List<CategoryModel>> = _categoryLive
    private val _isClickBtTopProd = MutableLiveData<Boolean>()
    val isClickBtTopProd: LiveData<Boolean> = _isClickBtTopProd
    private val _isRefresh = MutableLiveData<Boolean>()
    val isRefresh: LiveData<Boolean> = _isRefresh
    fun loadData() {
        Log.d("TAG", "loadData: ")
        _isRefresh.value = true
        getProductListUseCase.execute().onEach {
            Log.d("TAG", "getProduct:${it.size}")
            _productsLive.value = it
            _isRefresh.value = false
        }.launchIn(scope)

        getCategoryUseCase.execute().onEach {
            Log.d("TAG", "getCategory:${it.size}")
            _categoryLive.value = it
            _isRefresh.value = false

        }.launchIn(scope)

        _isClickBtTopProd.value = true
     }

    fun clickTopProductBt() {
        _isClickBtTopProd.value = true

    }

    fun clickRecommendedProdBt() {
        _isClickBtTopProd.value = false

    }

}