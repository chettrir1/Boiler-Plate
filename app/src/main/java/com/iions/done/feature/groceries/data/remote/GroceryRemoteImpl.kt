package com.iions.done.feature.groceries.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.iions.done.feature.groceries.data.GroceryRepository
import com.iions.done.feature.groceries.data.model.AddToCartResponse
import com.iions.done.feature.groceries.data.model.GroceryDetailRemoteBaseResponse
import com.iions.done.feature.groceries.data.model.GroceryResponse
import com.iions.done.feature.groceries.screen.detail.GroceryPagingSource
import com.iions.done.remote.ApiService
import com.iions.done.utils.notNullMapper
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
        return Pager(config = PagingConfig(pageSize = 15, maxSize = 2000),
            pagingSourceFactory = { GroceryPagingSource(apiService) }).flow
    }

    override suspend fun addToCart(
        token: String,
        userId: Int?,
        itemId: Int?,
        itemType: String?,
        quantity: Int?
    ): List<AddToCartResponse> {
        val requestParams = mutableMapOf<String, Any>()
        requestParams["user_id"] = userId ?: -1
        requestParams["item_id"] = itemId ?: -1
        requestParams["item_type"] = itemType ?: ""
        requestParams["quantity"] = quantity ?: 0
        val remoteResponse = apiService.requestAddToCart(token, requestParams)
        return notNullMapper(remoteResponse)
    }

    override suspend fun getGroceryDetail(itemId: Int): GroceryDetailRemoteBaseResponse {
        val requestParams = mutableMapOf<String, Any>()
        requestParams["item_id"] = itemId
        val remoteResponse = apiService.getGroceryDetail(requestParams)
        return notNullMapper(remoteResponse)
    }
}