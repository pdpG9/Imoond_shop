package com.imoond.data.repository.room.maps

interface Mapper<T,E> {
    fun mapData(entity:E):T

    fun mapEntity(data:T):E

    fun mapListEntity(list:List<T>):List<E>{
        return list.map {
            mapEntity(it)
        }
    }
    fun mapListData(list:List<E>):List<T>{
        return list.map {
            mapData(it)
        }
    }
}