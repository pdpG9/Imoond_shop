package com.imoond.data.models.productModels

import com.imoond.domain.model.customerModels.Self


data class Links(
    val collection: List<Collection>,
    val self: List<Self>
)