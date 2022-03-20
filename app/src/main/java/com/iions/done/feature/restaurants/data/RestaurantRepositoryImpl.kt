package com.iions.done.feature.restaurants.data

import com.iions.done.feature.groceries.data.model.AddToCartResponse
import com.iions.done.feature.restaurants.data.model.RestaurantRemoteBaseResponse
import com.iions.done.utils.SchedulersFactory
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: RestaurantRepository.Local,
    private val remoteRepository: RestaurantRepository.Remote
) : RestaurantRepository {
    override fun isUserLoggedIn(): Boolean {
        return localRepository.isUserLoggedIn()
    }

    override suspend fun getRestaurants(
        filter: String?,
        page: Int
    ): RestaurantRemoteBaseResponse? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.getRestaurants(filter, page)
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
}
