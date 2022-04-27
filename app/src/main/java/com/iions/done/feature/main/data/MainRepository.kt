package com.iions.done.feature.main.data

import androidx.lifecycle.LiveData
import com.iions.done.feature.auth.data.model.AddressResponse
import com.iions.done.feature.auth.data.model.LogoutResponse
import com.iions.done.feature.main.data.model.*
import java.io.File

interface MainRepository {
    fun isUserLoggedIn(): Boolean
    fun fetchModuleList(): LiveData<List<ModuleResponse>>
    fun fetchBannerList(): LiveData<List<BannerResponse>>
    fun fetchGroceryCategoryList(): LiveData<List<HomeGroceryCategoryResponse>>
    fun fetchGroceryList(categoryId: Int): LiveData<List<HomeGroceryResponse>>
    fun getAuthorizationToken(): String
    suspend fun requestLogout(token: String): LogoutResponse?
    suspend fun fetchCartList(token: String): CartBaseResponse?
    suspend fun fetchAddressList(): List<AddressResponse>?
    suspend fun removeCartList(authorizationToken: String, cartId: Int): RemoveCartResponse?
    suspend fun fetchProfileResponse(): ProfileBaseResponse?
    suspend fun fetchOrdersList(): OrdersBaseResponse?
    fun fetchRestaurantList(): LiveData<List<HomeRestaurantRemoteResponse>>
    suspend fun editProfile(name: String? = null, avatar: String? = null): EditProfileResponse?
    suspend fun fetchHomeResponse(): HomeResponse?
    suspend fun createOrder(file: File): CreateOrderResponse?

    interface Local {
        fun isUserLoggedIn(): Boolean
        fun fetchModuleList(): LiveData<List<ModuleResponse>>
        fun fetchBannerList(): LiveData<List<BannerResponse>>
        fun fetchGroceryCategoryList(): LiveData<List<HomeGroceryCategoryResponse>>
        fun fetchGroceryList(categoryId: Int): LiveData<List<HomeGroceryResponse>>
        fun getAuthorizationToken(): String
        suspend fun clearPrefs()
        suspend fun fetchAddressList(): List<AddressResponse>?
        fun fetchRestaurantList(): LiveData<List<HomeRestaurantRemoteResponse>>
        suspend fun fetchHomeResponse(response: HomeResponse?)
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
        suspend fun editProfile(
            token: String,
            name: String? = null,
            avatar: String? = null
        ): EditProfileResponse?

        suspend fun fetchHomeResponse(): HomeResponse?
        suspend fun createOrder(token: String, file: File): CreateOrderResponse?
    }
}