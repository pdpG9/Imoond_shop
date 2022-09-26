package com.example.imoondshop.ui.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imoond.domain.model.NotificationEntity

class NotificationViewModel : ViewModel() {
    private val _notifications = MutableLiveData<NotificationEntity>()
    val notifications :LiveData<NotificationEntity> = _notifications


}