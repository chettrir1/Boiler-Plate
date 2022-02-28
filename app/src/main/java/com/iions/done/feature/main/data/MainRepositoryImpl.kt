package com.iions.done.feature.main.data

import com.iions.done.feature.auth.data.model.LogoutResponse
import com.iions.done.feature.main.data.model.BannerResponse
import com.iions.done.feature.main.data.model.GroceryCategoryResponse
import com.iions.done.feature.main.data.model.GroceryResponse
import com.iions.done.feature.main.data.model.ModuleResponse
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

    override suspend fun fetchGroceryCategoryList(): List<GroceryCategoryResponse>? {
        return withContext(schedulersFactory.io()) {
            localRepository.fetchGroceryCategoryList()
        }
    }

    override suspend fun fetchGroceryList(): List<GroceryResponse>? {
        return withContext(schedulersFactory.io()) {
            localRepository.fetchGroceryList()
        }
    }

    override fun getAuthorizationToken(): String {
        return localRepository.getAuthorizationToken()
    }

    override suspend fun requestLogout(token: String): LogoutResponse? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.requestLogout(token)
        }
    }
}
