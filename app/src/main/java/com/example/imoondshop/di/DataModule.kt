package com.example.imoondshop.di

import android.content.Context
import android.content.SharedPreferences
import com.imoond.data.repository.imp.CategoryRepositoryImp
import com.imoond.data.repository.imp.LocalRepositoryImp
import com.imoond.data.repository.imp.ProductRepositoryImp
import com.imoond.data.repository.network.CategoryApi
import com.imoond.data.repository.network.Network
import com.imoond.data.repository.network.ProductApi
import com.imoond.data.untils.Constants
import com.imoond.domain.repository.CategoryRepository
import com.imoond.domain.repository.LocalRepository
import com.imoond.domain.repository.ProductRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<ProductApi> {
        Network.productApi
    }
    single<CategoryApi> {
        Network.categoryAi
    }
    single<ProductRepository> {
        ProductRepositoryImp(productApi = get())
    }
    single<CategoryRepository> {
        CategoryRepositoryImp(categoryApi = get())
    }
    single<SharedPreferences> {
        androidContext().getSharedPreferences(Constants.DB_NAME, Context.MODE_PRIVATE)
    }
    single<LocalRepository> {
        LocalRepositoryImp(pref = get())
    }

}