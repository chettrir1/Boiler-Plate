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
        saveGrocery(response?.grocery)
        saveBanner(response?.banners)
        saveDistrict(response?.district)
        saveStreet(response?.streets)
    }

    private suspend fun saveBanner(banners: List<BannerResponse>?) {
        databaseManager.getBannerDao().insert(BannersMapper.mapToLocal(banners ?: emptyList()))
    }

    private suspend fun saveModules(modules: List<ModuleResponse>?) {
        databaseManager.getModuleDao().insert(ModulesMapper.mapToLocal(modules ?: emptyList()))
    }

    private suspend fun saveGrocery(grocery: List<HomeGroceryRemoteResponse>?) {
        databaseManager.getGroceryDao().insert(GroceryMapper.mapToLocal(grocery ?: emptyList()))
        grocery?.forEach {
            it.brand?.let { data -> saveGroceryBrand(data) }
            it.category?.let { data -> saveGroceryCategory(data) }
        }
    }

    private suspend fun saveDistrict(district: List<DistrictResponse>?) {
        databaseManager.getDistrictDao().insert(DistrictMapper.mapToLocal(district ?: emptyList()))
    }

    private suspend fun saveStreet(street: List<StreetResponse>?) {
        databaseManager.getStreetDao().insert(StreetMapper.mapToLocal(street ?: emptyList()))
    }

    private suspend fun saveGroceryBrand(brand: HomeGroceyBrandResponse) {
        databaseManager.getGroceryBrandDao().insert(GroceryBrandMapper.mapToLocal(brand))
    }

    private suspend fun saveGroceryCategory(category: HomeGroceryCategoryResponse) {
        databaseManager.getGroceryCategoryDao().insert(GroceryCategoryMapper.mapToLocal(category))
    }
}
