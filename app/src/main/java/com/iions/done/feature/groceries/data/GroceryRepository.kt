package com.iions.done.feature.groceries.data

import androidx.paging.PagingData
import com.iions.done.feature.groceries.data.model.GroceryResponse
import kotlinx.coroutines.flow.Flow

interface GroceryRepository {
    suspend fun getGroceries(
        filter: String?,
        category: String?,
        brand: String?
    ): Flow<PagingData<GroceryResponse>>?

    interface Local {
    }

    interface Remote {
        suspend fun getGroceries(
            filter: String?,
            category: String?,
            brand: String?
        ): Flow<PagingData<GroceryResponse>>?
    }
}