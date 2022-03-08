package com.iions.done.feature.groceries.screen.detail

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.iions.done.feature.groceries.data.model.GroceryResponse
import com.iions.done.remote.ApiService

class GroceryPagingSource(val apiService: ApiService) : PagingSource<Int, GroceryResponse>() {
    override fun getRefreshKey(state: PagingState<Int, GroceryResponse>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GroceryResponse> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = apiService.getGroceries(nextPage)
            var nextPageNumber: Int? = null
            if (response.response?.items?.nextPageUrl != null) {
                val uri = Uri.parse(response.response?.items?.nextPageUrl)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }
            LoadResult.Page(
                data = response.response?.items?.data ?: emptyList(),
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}