package com.example.imoondshop.ui.account

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.imoond.data.data_source.ProductDataSource
import com.imoond.data.models.productModels.ProductModelItem
import com.imoond.data.repository.imp.ProductRepositoryImp
import com.imoond.data.repository.network.ProductApi
import kotlinx.coroutines.flow.Flow

class AccountViewModel(private val productApi: ProductApi) : ViewModel() {
    var productsFlow: Flow<PagingData<ProductModelItem>>? = null
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message
    private val _toast = MutableLiveData<String>()
    val toast: LiveData<String> = _toast
    private val _isShowLoader = MutableLiveData<Boolean>()
    val isShowLoader: LiveData<Boolean> = _isShowLoader

    init {
        Log.d("TAG", "init: ")
        productsFlow = Pager(
            config = PagingConfig(
                pageSize = ProductRepositoryImp.PAGE_SIZE
            ),
            pagingSourceFactory = {
                _isShowLoader.postValue(true)
                ProductDataSource(productApi)
            }
        ).flow
            .cachedIn(viewModelScope)
    }

}