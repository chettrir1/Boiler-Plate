package com.iions.done.feature.main.data

import com.iions.done.feature.auth.data.model.AddressResponse
import com.iions.done.feature.auth.data.model.LogoutResponse
import com.iions.done.feature.main.data.model.*
import com.iions.done.utils.SchedulersFactory
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: MainRepository.Local,
    private val remoteRepository: MainRepository.Remote
) : MainRepository {
    override fun isUserLoggedIn(): Boolean {
        return localRepository.isUserLoggedIn()
    }

    override suspend fun fetchModuleList(): List<ModuleResponse>? {
        return withContext(schedulersFactory.io()) {
            localRepository.fetchModuleList()
        }
    }

    override suspend fun fetchBannerList(): List<BannerResponse>? {
        return withContext(schedulersFactory.io()) {
            localRepository.fetchBannerList()
        }
    }

    override suspend fun fetchGroceryCategoryList(): List<HomeGroceryCategoryResponse>? {
        return withContext(schedulersFactory.io()) {
            localRepository.fetchGroceryCategoryList()
        }
    }

    override suspend fun fetchGroceryList(): List<HomeGroceryResponse>? {
        return withContext(schedulersFactory.io()) {
            localRepository.fetchGroceryList()
        }
    }

    override fun getAuthorizationToken(): String {
        return localRepository.getAuthorizationToken()
    }

    override suspend fun requestLogout(token: String): LogoutResponse? {
        return withContext(schedulersFactory.io()) {
            val response = remoteRepository.requestLogout(token)
            localRepository.clearPrefs()
            response
        }
    }

    override suspend fun fetchCartList(token: String): CartBaseResponse? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.fetchCartList(token)
        }
    }

    override suspend fun fetchAddressList(): List<AddressResponse>? {
        return withContext(schedulersFactory.io()) {
            localRepository.fetchAddressList()
        }
    }

    override suspend fun removeCartList(
        authorizationToken: String,
        cartId: Int
    ): RemoveCartResponse? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.removeCartList(authorizationToken, cartId)
        }
    }

    override suspend fun fetchProfileResponse(): ProfileBaseResponse? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.fetchProfileResponse(localRepository.getAuthorizationToken())
        }
    }

    override suspend fun fetchOrdersList(): OrdersBaseResponse? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.fetchOrdersList(localRepository.getAuthorizationToken())
        }
    }
}
