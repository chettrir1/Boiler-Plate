package com.iions.done.feature.restaurants.data.remote

import com.iions.done.exceptions.FailedResponseException
import com.iions.done.feature.groceries.data.model.AddToCartResponse
import com.iions.done.feature.groceries.data.model.GroceryRemoteBaseResponse
import com.iions.done.feature.restaurants.data.RestaurantRepository
import com.iions.done.feature.restaurants.data.model.RestaurantRemoteBaseResponse
import com.iions.done.remote.ApiService
import javax.inject.Inject

class RestaurantRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : RestaurantRepository.Remote {

    override suspend fun getRestaurants(
        filter: String?,
        page: Int,
    ): RestaurantRemoteBaseResponse? {
        val params: MutableMap<String, String> = HashMap()
        params["filter"] = filter ?: ""
        val remoteResponse = apiService.getRestaurants(page)
        if (remoteResponse.status == true) {
            throw FailedResponseException(
                remoteResponse.status!!,
                remoteResponse.message.toString()
            )
        } else {
            return remoteResponse.response
        }
    }

    override suspend fun addToCart(
        token: String,
        userId: Int?,
        itemId: Int?,
        itemType: String?,
        quantity: Int?
    ): AddToCartResponse? {
        val requestParams = mutableMapOf<String, Any>()
        requestParams["user_id"] = userId ?: -1
        requestParams["item_id"] = itemId ?: -1
        requestParams["item_type"] = itemType ?: ""
        requestParams["quantity"] = quantity ?: 0
        val remoteResponse = apiService.requestAddToCart(token, requestParams)
        if (remoteResponse.status == true) {
            throw FailedResponseException(
                remoteResponse.status!!,
                remoteResponse.message.toString()
            )
        } else {
            return remoteResponse.response
        }
    }
}