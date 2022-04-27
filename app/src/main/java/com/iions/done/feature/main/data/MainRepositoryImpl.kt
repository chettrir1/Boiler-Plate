package com.iions.done.feature.main.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.iions.done.feature.auth.data.model.AddressResponse
import com.iions.done.feature.auth.data.model.LogoutResponse
import com.iions.done.feature.main.data.model.*
import com.iions.done.utils.SchedulersFactory
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: MainRepository.Local,
    private val remoteRepository: MainRepository.Remote
) : MainRepository {
    override fun isUserLoggedIn(): Boolean {
        return localRepository.isUserLoggedIn()
    }

    override fun fetchModuleList(): LiveData<List<ModuleResponse>> {
        return liveData(schedulersFactory.io()) {
            emitSource(localRepository.fetchModuleList())
        }
    }

    override fun fetchBannerList(): LiveData<List<BannerResponse>> {
        return liveData(schedulersFactory.io()) {
            emitSource(localRepository.fetchBannerList())
        }
    }

    override fun fetchGroceryCategoryList(): LiveData<List<HomeGroceryCategoryResponse>> {
        return liveData(schedulersFactory.io()) {
            emitSource(localRepository.fetchGroceryCategoryList())
        }
    }

    override fun fetchGroceryList(categoryId: Int): LiveData<List<HomeGroceryResponse>> {
        return liveData(schedulersFactory.io()) {
            emitSource(localRepository.fetchGroceryList(categoryId))
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

    override fun fetchRestaurantList(): LiveData<List<HomeRestaurantRemoteResponse>> {
        return liveData(schedulersFactory.io()) {
            emitSource(localRepository.fetchRestaurantList())
        }
    }

    override suspend fun editProfile(name: String?, avatar: String?): EditProfileResponse? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.editProfile(localRepository.getAuthorizationToken(), name, avatar)
        }
    }

    override suspend fun fetchHomeResponse(): HomeResponse? {
        return withContext(schedulersFactory.io()) {
            val response = remoteRepository.fetchHomeResponse()
            localRepository.fetchHomeResponse(response)
            response
        }
    }

    override suspend fun createOrder(file: File): CreateOrderResponse? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.createOrder(localRepository.getAuthorizationToken(), file)
        }
    }
}
