package com.imoond.data.repository.network
import com.imoond.data.models.productModels.ProductModel
import com.imoond.data.models.productModels.ProductModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("/wp-json/wc/v3/products")
    suspend fun getAll(): Response<ProductModel>

    @GET("/wp-json/wc/v3/products/{id}")
    suspend fun getProductById(@Path("id") id:Int):Response<ProductModelItem>

}