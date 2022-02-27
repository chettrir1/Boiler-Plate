package com.iions.done.feature.main.data

import com.iions.done.feature.main.data.model.BannerResponse
import com.iions.done.feature.main.data.model.GroceryCategoryResponse
import com.iions.done.feature.main.data.model.GroceryResponse
import com.iions.done.feature.main.data.model.ModuleResponse

interface MainRepository {
    fun isUserLoggedIn(): Boolean
    suspend fun fetchModuleList(): List<ModuleResponse>?
    suspend fun fetchBannerList(): List<BannerResponse>?
    suspend fun fetchGroceryCategoryList(): List<GroceryCategoryResponse>?
    suspend fun fetchGroceryList(): List<GroceryResponse>?

    interface Local {
        fun isUserLoggedIn(): Boolean
        suspend fun fetchModuleList(): List<ModuleResponse>?
        suspend fun fetchBannerList(): List<BannerResponse>?
        suspend fun fetchGroceryCategoryList(): List<GroceryCategoryResponse>?
        suspend fun fetchGroceryList(): List<GroceryResponse>?
    }

    interface Remote {
    }
}