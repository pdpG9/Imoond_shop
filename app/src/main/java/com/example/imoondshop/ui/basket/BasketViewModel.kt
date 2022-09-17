package com.example.imoondshop.ui.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BasketViewModel : ViewModel() {
    private val _btAllCheck  = MutableLiveData<Boolean>(false)
    val btAllCheck:LiveData<Boolean> = _btAllCheck


    fun clickAllBtCheck(){
        _btAllCheck.value = !_btAllCheck.value!!
    }

}