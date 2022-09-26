package com.imoond.data.models.categroyModel

import com.imoond.data.models.productModels.Image
import com.imoond.data.models.productModels.Links

data class CategoryModelItem(
    val _links: Links?,
    val count: Int,
    val description: String,
    val display: String,
    val id: Int,
    val image: Image?,
    val menu_order: Int,
    val name: String,
    val parent: Int,
    val slug: String
)