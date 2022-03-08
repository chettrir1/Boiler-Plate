package com.iions.done.feature.main.data

import com.iions.done.feature.auth.data.model.LogoutResponse
import com.iions.done.feature.main.data.model.BannerResponse
import com.iions.done.feature.main.data.model.HomeGroceryCategoryResponse
import com.iions.done.feature.main.data.model.HomeGroceryResponse
import com.iions.done.feature.main.data.model.ModuleResponse

interface MainRepository {
    fun isUserLoggedIn(): Boolean
    suspend fun fetchModuleList(): List<ModuleResponse>?
    suspend fun fetchBannerList(): List<BannerResponse>?
    suspend fun fetchGroceryCategoryList(): List<HomeGroceryCategoryResponse>?
    suspend fun fetchGroceryList(): List<HomeGroceryResponse>?
    fun getAuthorizationToken(): String
    suspend fun requestLogout(token: String): LogoutResponse?

    interface Local {
        fun isUserLoggedIn(): Boolean
        suspend fun fetchModuleList(): List<ModuleResponse>?
        suspend fun fetchBannerList(): List<BannerResponse>?
        suspend fun fetchGroceryCategoryList(): List<HomeGroceryCategoryResponse>?
        suspend fun fetchGroceryList(): List<HomeGroceryResponse>?
        fun getAuthorizationToken(): String
    }

    interface Remote {
        suspend fun requestLogout(token: String): LogoutResponse?
    }
}