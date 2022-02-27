package com.iions.done.feature.splash.data.local

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.main.data.mapper.ModulesMapper
import com.iions.done.feature.main.data.mapper.GroceryBrandMapper
import com.iions.done.feature.main.data.mapper.GroceryCategoryMapper
import com.iions.done.feature.main.data.mapper.GroceryMapper
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
    }

    private suspend fun saveModules(modules: List<ModuleResponse>?) {
        databaseManager.getModuleDao().insert(ModulesMapper.mapToLocal(modules ?: emptyList()))
    }

    private suspend fun saveGrocery(grocery: List<GroceryRemoteResponse>?) {
        databaseManager.getGroceryDao().insert(GroceryMapper.mapToLocal(grocery ?: emptyList()))
        grocery?.forEach {
            it.brand?.let { data -> saveGroceryBrand(data) }
            it.category?.let { data-> saveGroceryCategory(data) }
        }
    }

    private suspend fun saveGroceryBrand(brand: BrandResponse) {
        databaseManager.getGroceryBrandDao().insert(GroceryBrandMapper.mapToLocal(brand))
    }

    private suspend fun saveGroceryCategory(category: GroceryCategoryResponse) {
        databaseManager.getGroceryCategoryDao().insert(GroceryCategoryMapper.mapToLocal(category))
    }
}
