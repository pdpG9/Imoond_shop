package com.imoond.domain.repository

import com.imoond.domain.model.NotificationEntity

interface NotificationRepository {
    fun getNotification(eventListener: EventListener<NotificationEntity>)
}