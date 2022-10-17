package com.imoond.data.data_source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.imoond.data.untils.DataPageLoader

open class BaseDataSource<T : Any>(private val loader: DataPageLoader<T>, private val pageSize: Int) :
    PagingSource<Int, T>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val pageIndex = params.key ?: 1
        return try {
            Log.d("TAG", "pageIndex - ${pageIndex}: ")
            val data = loader.invoke(pageIndex, params.loadSize)
            Log.d("TAG", "params.key - ${params.key}: ")
            Log.d("TAG", "params.loadSize - ${params.loadSize}: ")
            return LoadResult.Page(
                data = data,
                prevKey = if (pageIndex == 1) null else pageIndex - 1,
                nextKey = if (pageIndex==params.loadSize) null else pageIndex+1
//                nextKey = if (data.size == params.loadSize) pageIndex + (params.loadSize / pageSize) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}