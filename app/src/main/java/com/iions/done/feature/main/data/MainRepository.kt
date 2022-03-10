package com.iions.done.feature.main.data

import com.iions.done.feature.auth.data.model.LogoutResponse
import com.iions.done.feature.main.data.model.*

interface MainRepository {
    fun isUserLoggedIn(): Boolean
    suspend fun fetchModuleList(): List<ModuleResponse>?
    suspend fun fetchBannerList(): List<BannerResponse>?
    suspend fun fetchGroceryCategoryList(): List<HomeGroceryCategoryResponse>?
    suspend fun fetchGroceryList(): List<HomeGroceryResponse>?
    fun getAuthorizationToken(): String
    suspend fun requestLogout(token: String): List<LogoutResponse>?
    suspend fun fetchCartList(token: String): CartBaseResponse?

    interface Local {
        fun isUserLoggedIn(): Boolean
        suspend fun fetchModuleList(): List<ModuleResponse>?
        suspend fun fetchBannerList(): List<BannerResponse>?
        suspend fun fetchGroceryCategoryList(): List<HomeGroceryCategoryResponse>?
        suspend fun fetchGroceryList(): List<HomeGroceryResponse>?
        fun getAuthorizationToken(): String
        suspend fun clearPrefs()
    }

    interface Remote {
        suspend fun requestLogout(token: String): List<LogoutResponse>?
        suspend fun fetchCartList(token: String): CartBaseResponse?
    }
}