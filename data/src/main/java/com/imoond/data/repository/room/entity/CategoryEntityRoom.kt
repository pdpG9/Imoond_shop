package com.imoond.data.repository.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntityRoom(
    @PrimaryKey
     val id:Int,
     val name:String,
     val images:String


)
