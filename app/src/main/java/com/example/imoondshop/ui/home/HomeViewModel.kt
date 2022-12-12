package com.example.imoondshop.ui.home

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.imoond.data.data_source.ProductDataSource
import com.imoond.data.models.productModels.ProductModelItem
import com.imoond.data.repository.imp.ProductRepositoryImp
import com.imoond.data.repository.network.ProductApi
import com.imoond.domain.model.CategoryEntity
import com.imoond.domain.model.ProductEntity
import com.imoond.domain.repository.EventListener
import com.imoond.domain.usecase.category.GetCategoryUseCase
import com.imoond.domain.usecase.local.GetAccountUseCase
import com.imoond.domain.usecase.product.GetProductListUseCase
import kotlinx.coroutines.flow.Flow

class HomeViewModel(
    private val productApi: ProductApi,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getAccountUseCase: GetAccountUseCase
) : ViewModel() {
    private val _isShowProgress = MutableLiveData<Boolean>()
    val isShowProgress: LiveData<Boolean> = _isShowProgress
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message
    private val _categoryLive = MutableLiveData<List<CategoryEntity>>()
    val categoryLive: LiveData<List<CategoryEntity>> = _categoryLive
    private val _isClickBtTopProd = MutableLiveData<Boolean>()
    val isClickBtTopProd: LiveData<Boolean> = _isClickBtTopProd
    private val _isHaveAccount = MutableLiveData<Boolean>()
    val isHaveAccount: LiveData<Boolean> = _isHaveAccount
    var productsFlow: Flow<PagingData<ProductModelItem>>? = null

    init {
        Log.d("TAG", "init: ")
        productsFlow = Pager(
            config = PagingConfig(
                pageSize = ProductRepositoryImp.PAGE_SIZE
            ),
            pagingSourceFactory = {
                _isShowProgress.postValue(true)
                ProductDataSource(productApi)
            }
        ).flow
            .cachedIn(viewModelScope)
    }


    suspend fun loadData() {
        Log.d("TAG", "loadData: ")


        getAccountUseCase.execute(object :EventListener<Int>{
            override fun success(data: Int) {
                _isHaveAccount.postValue(true)
            }

            override fun error(message: String) {
               _isHaveAccount.postValue(false)
            }

            override fun empty() {
               _isHaveAccount.postValue(false)
            }

            override fun load(l: Boolean) {

            }

        })
//        getProductListUseCase.execute(object : EventListener<List<ProductEntity>> {
//            override fun success(data: List<ProductEntity>) {
//                _productsLive.postValue(data)
//            }
//
//            override fun error(message: String) {
//                _message.postValue(message)
//            }
//
//            override fun empty() {
//                _message.postValue("empty")
//            }
//
//            override fun load(l: Boolean) {
//                _isShowProgress.postValue(l)
//            }
//
//        })
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