package com.iions.done.feature.main.data.local

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.auth.data.model.AddressResponse
import com.iions.done.feature.main.data.MainRepository
import com.iions.done.feature.main.data.model.*
import javax.inject.Inject

class MainLocalImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val databaseManager: DatabaseManager
) : MainRepository.Local {

    override fun isUserLoggedIn(): Boolean {
        return sharedPreferenceManager.username.isNotEmpty()
    }

    override suspend fun fetchModuleList(): List<ModuleResponse> {
        return databaseManager.getModuleDao().getCategoryResponse()
    }

    override suspend fun fetchBannerList(): List<BannerResponse> {
        return databaseManager.getBannerDao().getBannerResponse()
    }

    override suspend fun fetchGroceryCategoryList(): List<HomeGroceryCategoryResponse>? {
        return databaseManager.getGroceryCategoryDao().getGroceryCategoryResponse()
    }

    override suspend fun fetchGroceryList(categoryId: Int): List<HomeGroceryResponse>? {
        return databaseManager.getGroceryDao().getGroceryResponse(categoryId)
    }

    override fun getAuthorizationToken(): String {
        return sharedPreferenceManager.accessToken.toString()
    }

    override suspend fun clearPrefs() {
        sharedPreferenceManager.clearCache()
    }

    override suspend fun fetchAddressList(): List<AddressResponse> {
        return databaseManager.getUserAddressDao().getUserAddressResponse()
    }

    override suspend fun fetchRestaurantList(): List<HomeRestaurantRemoteResponse>? {
        return databaseManager.getRestaurantDao().getRestaurantResponse()
    }

}
