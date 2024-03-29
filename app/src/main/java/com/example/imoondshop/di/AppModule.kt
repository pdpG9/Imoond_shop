package com.example.imoondshop.di

import com.example.imoondshop.ui.account.AccountViewModel
import com.example.imoondshop.ui.basket.BasketViewModel
import com.example.imoondshop.ui.buy.BuyViewModel
import com.example.imoondshop.ui.category.CategoryViewModel
import com.example.imoondshop.ui.home.HomeViewModel
import com.example.imoondshop.ui.product_info.ProductInfoViewModel
import com.example.imoondshop.ui.products.ProductListViewModel
import com.example.imoondshop.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<HomeViewModel> {
        HomeViewModel(
            productApi = get(),
            getCategoryUseCase = get(),
            getAccountUseCase = get()
        )
    }

    viewModel<CategoryViewModel> {
        CategoryViewModel(getCategoryUseCase = get())
    }
    viewModel<ProductInfoViewModel> {
        ProductInfoViewModel(
            getProductByIdUseCase = get(),
            addProductToCardUseCase = get(),
            getProductsFromBasketUseCase = get()
        )
    }
    viewModel<SearchViewModel> {
        SearchViewModel(getProductByNameUseCase = get())
    }
    viewModel<ProductListViewModel> {
        ProductListViewModel(getProductsByCategory = get())
    }
    viewModel<BuyViewModel> {
        BuyViewModel()
    }
    viewModel<BasketViewModel> {
        BasketViewModel(
            repository = get(),
            getProductByIdUseCase = get(),
            getProductsByCategory = get()
        )
    }
    viewModel<AccountViewModel> {
        AccountViewModel(productApi = get())
    }

}