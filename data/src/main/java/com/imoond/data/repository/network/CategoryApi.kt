package com.imoond.data.repository.network

import com.imoond.data.models.categroyModel.CategoryModel
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApi {
    @GET("/wp-json/wc/v3/products/categories")
    suspend fun getCategories(): Response<CategoryModel>
}