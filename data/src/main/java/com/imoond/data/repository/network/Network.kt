package com.imoond.data.repository.network
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.imoond.data.untils.Constants

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    private val logging = HttpLoggingInterceptor(logger = object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.d("TAG", "log: $message")
        }
    })

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor {
            val request = it.request()
            Log.d("TAG", "request url:${request.url} ")
            val newRequest = request.newBuilder()
//                .addHeader("consumer_key",Constants.CONSUMER_KEY)
//                .addHeader("consumer_secret",Constants.CONSUMER_SECRET)
                .url("${request.url}?consumer_key=${Constants.CONSUMER_KEY}&consumer_secret=${Constants.CONSUMER_SECRET}")
                .build()
            val responseNew = it.proceed(newRequest)
            Log.d("TAG", "new request url:${request.url} ")
            return@addInterceptor responseNew
        }
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val productApi: ProductApi by lazy { retrofit.create(ProductApi::class.java) }
    val categoryAi:CategoryApi by lazy { retrofit.create(CategoryApi::class.java) }
}