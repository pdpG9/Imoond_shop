package com.imoond.domain.repository

interface NotificationRepository {
    fun getNotification(eventListener: EventListener)
}