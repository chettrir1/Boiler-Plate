package com.iions.done.feature.groceries.data.remote

import com.iions.done.feature.groceries.data.GroceryRepository
import com.iions.done.feature.groceries.data.model.AddToCartResponse
import com.iions.done.feature.groceries.data.model.GroceryDetailRemoteBaseResponse
import com.iions.done.feature.groceries.data.model.GroceryRemoteBaseResponse
import com.iions.done.remote.ApiService
import com.iions.done.utils.notNullMapper
import javax.inject.Inject

class GroceryRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : GroceryRepository.Remote {

    override suspend fun getGroceries(
        filter: String?,
        category: String?,
        brand: String?,
        page: Int,
    ): GroceryRemoteBaseResponse {
        val params: MutableMap<String, String> = HashMap()
        params["filter"] = filter ?: ""
        params["category"] = category ?: ""
        params["brand"] = brand ?: ""
        val remoteResponse = apiService.getGroceries(page)
        return notNullMapper(remoteResponse)
    }

    override suspend fun addToCart(
        token: String,
        userId: Int?,
        itemId: Int?,
        itemType: String?,
        quantity: Int?
    ): AddToCartResponse {
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