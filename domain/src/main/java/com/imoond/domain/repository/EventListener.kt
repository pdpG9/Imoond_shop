package com.imoond.domain.repository

interface EventListener<T> {
    fun success(data: T)
    fun error(message:String)
    fun empty()
    fun load(l:Boolean)
}
