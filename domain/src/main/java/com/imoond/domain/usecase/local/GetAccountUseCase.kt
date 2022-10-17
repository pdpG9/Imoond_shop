package com.imoond.domain.usecase.local

import com.imoond.domain.repository.EventListener
import com.imoond.domain.repository.LocalRepository

class GetAccountUseCase(private val repository: LocalRepository) {
    fun execute(eventListener: EventListener<Int>) = repository.getAccount(eventListener)
}