package com.iions.done.feature.groceries.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.iions.done.feature.groceries.data.GroceryRepository
import com.iions.done.feature.groceries.data.model.GroceryResponse
import com.iions.done.feature.groceries.screen.detail.GroceryPagingSource
import com.iions.done.remote.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GroceryRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : GroceryRepository.Remote {

    override suspend fun getGroceries(
        filter: String?,
        category: String?,
        brand: String?
    ): Flow<PagingData<GroceryResponse>> {
        val params: MutableMap<String, String> = HashMap()
        params["filter"] = filter ?: ""
        params["category"] = category ?: ""
        params["brand"] = brand ?: ""
        return Pager(config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = { GroceryPagingSource(apiService) }).flow
    }
}