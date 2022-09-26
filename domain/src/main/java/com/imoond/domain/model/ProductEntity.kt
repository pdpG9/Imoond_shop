package com.imoond.domain.model

import java.io.Serializable

data class ProductEntity(
    val id:Int,
    val name:String,
    val description:String,
    val short_description:String,
    val price:String,
    val regular_price:String,
    val sale_price:String,
    val weight:String,
    val length:String,
    val width:String,
    val height:String,
    val images:List<String>,
    val category:String,
    var cardCount:Int = 1,
    var isSelected:Boolean = false

):Serializable
