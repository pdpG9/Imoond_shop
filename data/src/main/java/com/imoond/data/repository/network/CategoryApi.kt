package com.imoond.data.repository.network

import com.imoond.data.models.categroyModel.CategoryModel
import com.imoond.data.untils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryApi {
    @GET("/wp-json/wc/v3/products/categories")
    suspend fun getCategories(
        @Query(Constants.consumer_key) consumer_key: String,
        @Query(Constants.consumer_secret) consumer_secret: String
    ): Response<CategoryModel>
}