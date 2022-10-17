package com.example.imoondshop.di

import com.imoond.domain.usecase.*
import com.imoond.domain.usecase.category.GetCategoryUseCase
import com.imoond.domain.usecase.local.*
import com.imoond.domain.usecase.product.*
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetProductByPageUseCase(repository = get())
    }
    factory<GetProductListUseCase> {
        GetProductListUseCase(repository = get())
    }
    factory<GetProductByIdUseCase> {
        GetProductByIdUseCase(repository = get())
    }
    factory<GetProductByNameUseCase> {
        GetProductByNameUseCase(repository = get())
    }
    factory<GetProductsByCategory> {
        GetProductsByCategory(repository = get())
    }
    factory<GetTopProductsUseCase> {
        GetTopProductsUseCase(repository = get())
    }
    factory<GetRecommendedUseCase> {
        GetRecommendedUseCase(repository = get())
    }
    factory<GetCategoryUseCase> {
        GetCategoryUseCase(repository = get())
    }
    factory<AddProductToCardUseCase> {
        AddProductToCardUseCase(repository = get())
    }
    factory<GetProductsFromBasketUseCase> {
        GetProductsFromBasketUseCase(repository = get())
    }
    factory<DeleteProductFromCardUseCase> {
        DeleteProductFromCardUseCase(repository = get())
    }
    factory<GetAccountUseCase> {
        GetAccountUseCase(repository = get())
    }
    factory<SaveAccountUseCase> {
        SaveAccountUseCase( )
    }

}