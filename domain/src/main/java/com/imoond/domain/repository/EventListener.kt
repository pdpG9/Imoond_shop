package com.imoond.domain.repository

interface EventListener {
    fun render(event: Event)
}
sealed class Event{
    class Success<T>(val data:T):Event()
    class Error(val error:String):Event()
    object Loading:Event()
    object Empty:Event()
}