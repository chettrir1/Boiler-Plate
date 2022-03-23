package com.iions.done.feature.splash.data.local

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.main.data.mapper.*
import com.iions.done.feature.main.data.model.*
import com.iions.done.feature.splash.data.SplashRepository
import javax.inject.Inject

class SplashLocalImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val databaseManager: DatabaseManager
) : SplashRepository.Local {

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
