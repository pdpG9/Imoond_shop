package com.imoond.data.data_source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.imoond.data.models.productModels.ProductModelItem
import com.imoond.data.repository.network.ProductApi
import com.imoond.data.untils.Constants
import retrofit2.Response

class ProductDataSource(private val productApi: ProductApi) :
    PagingSource<Int, ProductModelItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductModelItem> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = getAllProductsFromNetwork(page = nextPage)
            Log.d("TAG", "ProductDataSource->load->response.message: ${response.message()}")
            Log.d("TAG", "ProductDataSource->load->response.code: ${response.code()}")
            Log.d("TAG", "params.key - ${params.key}: ")
            Log.d("TAG", "params.loadSize - ${params.loadSize}: ")

            LoadResult.Page(
                data = response.body()!!,
                prevKey = if (nextPage==1) null else nextPage-1,
                nextKey = if (nextPage==params.loadSize) null else nextPage+1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ProductModelItem>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
 
    private suspend fun getAllProductsFromNetwork(
        page: Int
    ): Response<List<ProductModelItem>> {
        return productApi.getProducts(
            consumer_secret = Constants.CONSUMER_SECRET,
            consumer_key = Constants.CONSUMER_KEY,
            page = page
        )
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }

}