package com.example.imoondshop.ui.category

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imoond.domain.model.CategoryModel
import com.imoond.domain.usecase.GetCategoryUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CategoryViewModel(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val scope: LifecycleCoroutineScope
) : ViewModel() {
    private val _categoryLive = MutableLiveData<List<CategoryModel>>()
     val categoryLive:LiveData<List<CategoryModel>> = _categoryLive
    private val _isRefresh = MutableLiveData<Boolean>()
    val isRefresh = _isRefresh
    fun loadData() {
        Log.d("TAG", "loadData: ")
        isRefresh.value = true
        getCategoryUseCase.execute().onEach {
            Log.d("TAG", "loadData:${it.size}")
            _categoryLive.value = it
            isRefresh.value = false
        }.launchIn(scope)
    }
}