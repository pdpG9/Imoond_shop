package com.imoond.data.repository.network

import com.imoond.data.models.productModels.ProductModel
import com.imoond.data.models.productModels.ProductModelItem
import com.imoond.data.untils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("/wp-json/wc/v3/products/")
    suspend fun getAll(
        @Query(Constants.consumer_key) consumer_key: String,
        @Query(Constants.consumer_secret) consumer_secret: String
    ): Response<ProductModel>

    @GET("/wp-json/wc/v3/products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int,
        @Query(Constants.consumer_key) consumer_key: String,
        @Query(Constants.consumer_secret) consumer_secret: String
    ): Response<ProductModelItem>

    @GET("/wp-json/wc/v3/products")
    suspend fun getProducts(
        @Query("page") page: Int,
        @Query(Constants.consumer_key) consumer_key: String,
        @Query(Constants.consumer_secret) consumer_secret: String
    ): Response<List<ProductModelItem>>

}