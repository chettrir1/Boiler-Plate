package com.iions.done.feature.main.data.local

import androidx.lifecycle.LiveData
import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.auth.data.model.AddressResponse
import com.iions.done.feature.main.data.MainRepository
import com.iions.done.feature.main.data.mapper.*
import com.iions.done.feature.main.data.model.*
import javax.inject.Inject

class MainLocalImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val databaseManager: DatabaseManager
) : MainRepository.Local {

    override fun isUserLoggedIn(): Boolean {
        return sharedPreferenceManager.username.isNotEmpty()
    }

    override fun fetchModuleList(): LiveData<List<ModuleResponse>> {
        return databaseManager.getModuleDao().getCategoryResponse()
    }

    override fun fetchBannerList(): LiveData<List<BannerResponse>> {
        return databaseManager.getBannerDao().getBannerResponse()
    }

    override fun fetchGroceryCategoryList(): LiveData<List<HomeGroceryCategoryResponse>> {
        return databaseManager.getGroceryCategoryDao().getGroceryCategoryResponse()
    }

    override fun fetchGroceryList(categoryId: Int): LiveData<List<HomeGroceryResponse>> {
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

    override fun fetchRestaurantList(): LiveData<List<HomeRestaurantRemoteResponse>> {
        return databaseManager.getRestaurantDao().getRestaurantResponse()
    }

    override suspend fun fetchHomeResponse(response: HomeResponse?) {
        saveModules(response?.modules)
        response?.category?.let { saveGroceryCategory(it) }
        saveBanner(response?.banners)
        saveDistrict(response?.district)
        saveStreet(response?.streets)
        saveRestaurant(response?.restaurant)
    }

    private suspend fun saveBanner(banners: List<BannerResponse>?) {
        databaseManager.getBannerDao().insert(BannersMapper.mapToLocal(banners ?: emptyList()))
    }

    private suspend fun saveModules(modules: List<ModuleResponse>?) {
        databaseManager.getModuleDao().insert(ModulesMapper.mapToLocal(modules ?: emptyList()))
    }

    private suspend fun saveGroceryCategory(category: List<HomeGroceryRemoteResponse>) {
        databaseManager.getGroceryCategoryDao().insert(GroceryCategoryMapper.mapToLocal(category))
        category.forEach {
            it.grocery?.let { data -> saveGrocery(data) }
        }
    }

    private suspend fun saveGrocery(grocery: List<HomeGroceryResponse>?) {
        databaseManager.getGroceryDao()
            .insert(GroceryMapper.mapToLocal(grocery ?: emptyList()))
    }

    private suspend fun saveDistrict(district: List<DistrictResponse>?) {
        databaseManager.getDistrictDao().insert(DistrictMapper.mapToLocal(district ?: emptyList()))
    }

    private suspend fun saveStreet(street: List<StreetResponse>?) {
        databaseManager.getStreetDao().insert(StreetMapper.mapToLocal(street ?: emptyList()))
    }

    private suspend fun saveRestaurant(restaurant: List<HomeRestaurantRemoteResponse>?) {
        databaseManager.getRestaurantDao()
            .insert(RestaurantMapper.mapToLocal(restaurant ?: emptyList()))
    }
}
