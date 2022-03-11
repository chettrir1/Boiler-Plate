package com.iions.done.feature.groceries.data

import com.iions.done.feature.groceries.data.model.AddToCartResponse
import com.iions.done.feature.groceries.data.model.GroceryDetailRemoteBaseResponse
import com.iions.done.feature.groceries.data.model.GroceryRemoteBaseResponse
import com.iions.done.utils.SchedulersFactory
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GroceryRepositoryImpl @Inject constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: GroceryRepository.Local,
    private val remoteRepository: GroceryRepository.Remote
) : GroceryRepository {
    override fun isUserLoggedIn(): Boolean {
        return localRepository.isUserLoggedIn()
    }

    override suspend fun getGroceries(
        filter: String?,
        category: String?,
        brand: String?,
        page: Int
    ): GroceryRemoteBaseResponse? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.getGroceries(filter, category, brand, page)
        }
    }

    override fun getUserId(): Int {
        return localRepository.getUserId()
    }

    override suspend fun addToCart(
        itemId: Int?, itemType: String?, quantity: Int?
    ): AddToCartResponse? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.addToCart(
                localRepository.getAuthorizationToken(),
                localRepository.getUserId(),
                itemId,
                itemType,
                quantity
            )
        }
    }

    override suspend fun getGroceryDetail(itemId: Int): GroceryDetailRemoteBaseResponse? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.getGroceryDetail(itemId)
        }
    }
}
