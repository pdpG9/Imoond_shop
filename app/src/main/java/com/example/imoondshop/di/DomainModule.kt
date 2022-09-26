package com.example.imoondshop.di

import com.imoond.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {

    factory <GetProductListUseCase>{
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

}