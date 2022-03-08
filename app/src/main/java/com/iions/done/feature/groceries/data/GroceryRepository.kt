package com.iions.done.feature.groceries.data

import androidx.paging.PagingData
import com.iions.done.feature.groceries.data.model.AddToCartResponse
import com.iions.done.feature.groceries.data.model.GroceryResponse
import kotlinx.coroutines.flow.Flow

interface GroceryRepository {
    suspend fun getGroceries(
        filter: String?,
        category: String?,
        brand: String?
    ): Flow<PagingData<GroceryResponse>>?

    fun getUserId(): Int

    suspend fun addToCart(
        userId: Int?, itemId: Int?, itemType: String?
    ): List<AddToCartResponse>?

    interface Local {
        fun getUserId(): Int
    }

    interface Remote {
        suspend fun getGroceries(
            filter: String?,
            category: String?,
            brand: String?
        ): Flow<PagingData<GroceryResponse>>?

        suspend fun addToCart(
            userId: Int?, itemId: Int?, itemType: String?
        ): List<AddToCartResponse>?

    }
}