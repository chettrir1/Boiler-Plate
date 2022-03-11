package com.iions.done.feature.groceries.data

import com.iions.done.feature.groceries.data.model.AddToCartResponse
import com.iions.done.feature.groceries.data.model.GroceryDetailRemoteBaseResponse
import com.iions.done.feature.groceries.data.model.GroceryRemoteBaseResponse

interface GroceryRepository {
    fun isUserLoggedIn(): Boolean

    suspend fun getGroceries(
        filter: String?,
        category: String?,
        brand: String?,
        page: Int
    ): GroceryRemoteBaseResponse?

    fun getUserId(): Int
    suspend fun addToCart(
        itemId: Int?, itemType: String?, quantity: Int?
    ): AddToCartResponse?

    suspend fun getGroceryDetail(itemId: Int): GroceryDetailRemoteBaseResponse?

    interface Local {
        fun getAuthorizationToken(): String
        fun isUserLoggedIn(): Boolean
        fun getUserId(): Int
    }

    interface Remote {
        suspend fun getGroceries(
            filter: String?,
            category: String?,
            brand: String?,
            page: Int
        ): GroceryRemoteBaseResponse?

        suspend fun addToCart(
            token: String, userId: Int?, itemId: Int?, itemType: String?, quantity: Int?
        ): AddToCartResponse?

        suspend fun getGroceryDetail(itemId: Int): GroceryDetailRemoteBaseResponse?

    }
}