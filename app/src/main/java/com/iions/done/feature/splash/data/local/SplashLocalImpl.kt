package com.iions.done.feature.splash.data.local

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.main.data.mapper.CategoryMapper
import com.iions.done.feature.main.data.mapper.GroceryBrandMapper
import com.iions.done.feature.main.data.mapper.GroceryMapper
import com.iions.done.feature.main.data.model.BrandResponse
import com.iions.done.feature.main.data.model.CategoryResponse
import com.iions.done.feature.main.data.model.GroceryResponse
import com.iions.done.feature.main.data.model.HomeResponse
import com.iions.done.feature.splash.data.SplashRepository
import javax.inject.Inject

class SplashLocalImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val databaseManager: DatabaseManager
) : SplashRepository.Local {

    override suspend fun fetchHomeResponse(response: HomeResponse?) {
        saveCategory(response?.modules)
        saveGrocery(response?.grocery)
    }

    private suspend fun saveCategory(category: List<CategoryResponse>?) {
        databaseManager.getCategoryDao().insert(CategoryMapper.mapToLocal(category ?: emptyList()))
    }

    private suspend fun saveGrocery(grocery: List<GroceryResponse>?) {
        databaseManager.getGroceryDao().insert(GroceryMapper.mapToLocal(grocery ?: emptyList()))
        grocery?.forEach {
            it.brand?.let { it1 -> saveGroceryBrand(it1) }
        }
    }

    private suspend fun saveGroceryBrand(brand: BrandResponse) {
        databaseManager.getGroceryBrandDao().insert(GroceryBrandMapper.mapToLocal(brand))
    }
}
