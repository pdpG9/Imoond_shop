package com.example.imoondshop.ui.category

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imoond.domain.model.CategoryEntity
import com.imoond.domain.repository.EventListener
import com.imoond.domain.usecase.GetCategoryUseCase

class CategoryViewModel(
    private val getCategoryUseCase: GetCategoryUseCase,
) : ViewModel() {
    private val _categoryLive = MutableLiveData<List<CategoryEntity>>()
     val categoryLive:LiveData<List<CategoryEntity>> = _categoryLive
    private val _isShowProgress = MutableLiveData<Boolean>()
    val isShowProgress = _isShowProgress
    private val _message = MutableLiveData<String>()
    val message:LiveData<String> = _message
    suspend fun loadData() {
        Log.d("TAG", "loadData: ")
        _isShowProgress.postValue(true)
        getCategoryUseCase.execute(object :EventListener<List<CategoryEntity>>{
            override fun error(message: String) {
                _message.postValue(message)
            }

            override fun empty() {
                _message.postValue("empty")
            }

            override fun load(l: Boolean) {
                _isShowProgress.postValue(l)
            }


            override fun success(data: List<CategoryEntity>) {
                _categoryLive.postValue(data)
            }

        })
    }
}