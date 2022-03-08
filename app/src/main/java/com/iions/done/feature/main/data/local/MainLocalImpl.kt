package com.iions.done.feature.main.data.local

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.main.data.MainRepository
import com.iions.done.feature.main.data.model.BannerResponse
import com.iions.done.feature.main.data.model.HomeGroceryCategoryResponse
import com.iions.done.feature.main.data.model.HomeGroceryResponse
import com.iions.done.feature.main.data.model.ModuleResponse
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

    override suspend fun fetchBannerList(): List<BannerResponse>? {
        val packs = arrayListOf<BannerResponse>()
        packs.add(BannerResponse("https://cdn.dribbble.com/users/7361011/screenshots/15362916/food-banner---2_4x.jpg"))
        packs.add(BannerResponse("https://graphicsfamily.com/wp-content/uploads/edd/2020/11/Tasty-Food-Web-Banner-Design-scaled.jpg"))
        packs.add(BannerResponse("https://i.pinimg.com/736x/92/e3/0d/92e30ddc44098278a08add44b8403cc7.jpg"))
        packs.add(BannerResponse("https://img.freepik.com/free-psd/fast-food-restaurant-banner-template_23-2148987500.jpg?size=626&ext=jpg"))
        return packs
    }

    override suspend fun fetchGroceryCategoryList(): List<HomeGroceryCategoryResponse>? {
        return databaseManager.getGroceryCategoryDao().getGroceryCategoryResponse()
    }

    override suspend fun fetchGroceryList(): List<HomeGroceryResponse>? {
        return databaseManager.getGroceryDao().getGroceryResponse()
    }

    override fun getAuthorizationToken(): String {
        return sharedPreferenceManager.accessToken.toString()
    }
}
