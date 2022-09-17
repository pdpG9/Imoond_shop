package com.imoond.data.models.productModels

import com.imoond.domain.model.productModels.Self
import com.imoond.domain.model.productModels.Collection

data class Links(
    val collection: List<Collection>,
    val self: List<Self>
)