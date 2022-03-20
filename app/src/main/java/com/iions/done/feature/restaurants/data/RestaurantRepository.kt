package com.iions.done.feature.restaurants.data

import com.iions.done.feature.groceries.data.model.AddToCartResponse
import com.iions.done.feature.groceries.data.model.GroceryRemoteBaseResponse
import com.iions.done.feature.restaurants.data.model.RestaurantRemoteBaseResponse

interface RestaurantRepository {
    fun isUserLoggedIn(): Boolean
    suspend fun getRestaurants(
        filter: String?,
        page: Int
    ): RestaurantRemoteBaseResponse?

    fun getUserId(): Int
    suspend fun addToCart(
        itemId: Int?, itemType: String?, quantity: Int?
    ): AddToCartResponse?

    interface Local {
        fun getAuthorizationToken(): String
        fun isUserLoggedIn(): Boolean
        fun getUserId(): Int
    }

    interface Remote {
        suspend fun getRestaurants(
            filter: String?,
            page: Int
        ): RestaurantRemoteBaseResponse?

        suspend fun addToCart(
            token: String, userId: Int?, itemId: Int?, itemType: String?, quantity: Int?
        ): AddToCartResponse?

    }
}