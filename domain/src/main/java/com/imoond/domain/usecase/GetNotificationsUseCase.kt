package com.imoond.domain.usecase

import com.imoond.domain.model.NotificationEntity
import com.imoond.domain.repository.EventListener
import com.imoond.domain.repository.NotificationRepository

class GetNotificationsUseCase(private val repository: NotificationRepository) {
   suspend fun execute(eventListener: EventListener<NotificationEntity>) = repository.getNotification(eventListener)
}