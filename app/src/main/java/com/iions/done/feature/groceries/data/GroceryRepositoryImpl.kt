package com.iions.done.feature.groceries.data

import androidx.paging.PagingData
import com.iions.done.feature.groceries.data.model.AddToCartResponse
import com.iions.done.feature.groceries.data.model.GroceryResponse
import com.iions.done.utils.SchedulersFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GroceryRepositoryImpl @Inject constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: GroceryRepository.Local,
    private val remoteRepository: GroceryRepository.Remote
) : GroceryRepository {

    override suspend fun getGroceries(
        filter: String?,
        category: String?,
        brand: String?
    ): Flow<PagingData<GroceryResponse>>? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.getGroceries(filter, category, brand)
        }
    }

    override fun getUserId(): Int {
        return localRepository.getUserId()
    }

    override suspend fun addToCart(
        userId: Int?, itemId: Int?, itemType: String?
    ): List<AddToCartResponse>? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.addToCart(userId, itemId, itemType)
        }
    }
}
