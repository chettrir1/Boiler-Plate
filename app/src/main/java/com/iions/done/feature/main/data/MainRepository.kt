package com.iions.done.feature.main.data

import com.iions.done.feature.auth.data.model.AddressResponse
import com.iions.done.feature.auth.data.model.LogoutResponse
import com.iions.done.feature.main.data.model.*

interface MainRepository {
    fun isUserLoggedIn(): Boolean
    suspend fun fetchModuleList(): List<ModuleResponse>?
    suspend fun fetchBannerList(): List<BannerResponse>?
    suspend fun fetchGroceryCategoryList(): List<HomeGroceryCategoryResponse>?
    suspend fun fetchGroceryList(categoryId: Int): List<HomeGroceryResponse>?
    fun getAuthorizationToken(): String
    suspend fun requestLogout(token: String): LogoutResponse?
    suspend fun fetchCartList(token: String): CartBaseResponse?
    suspend fun fetchAddressList(): List<AddressResponse>?
    suspend fun removeCartList(authorizationToken: String, cartId: Int): RemoveCartResponse?
    suspend fun fetchProfileResponse(): ProfileBaseResponse?
    suspend fun fetchOrdersList(): OrdersBaseResponse?
    suspend fun fetchRestaurantList(): List<HomeRestaurantRemoteResponse>?

    interface Local {
        fun isUserLoggedIn(): Boolean
        suspend fun fetchModuleList(): List<ModuleResponse>?
        suspend fun fetchBannerList(): List<BannerResponse>?
        suspend fun fetchGroceryCategoryList(): List<HomeGroceryCategoryResponse>?
        suspend fun fetchGroceryList(categoryId: Int): List<HomeGroceryResponse>?
        fun getAuthorizationToken(): String
        suspend fun clearPrefs()
        suspend fun fetchAddressList(): List<AddressResponse>?
        suspend fun fetchRestaurantList(): List<HomeRestaurantRemoteResponse>?

    }

    interface Remote {
        suspend fun requestLogout(token: String): LogoutResponse?
        suspend fun fetchCartList(token: String): CartBaseResponse?
        suspend fun removeCartList(
            authorizationToken: String,
            cartId: Int
        ): RemoveCartResponse?

        suspend fun fetchProfileResponse(authorizationToken: String): ProfileBaseResponse?
        suspend fun fetchOrdersList(token: String): OrdersBaseResponse?
    }
}