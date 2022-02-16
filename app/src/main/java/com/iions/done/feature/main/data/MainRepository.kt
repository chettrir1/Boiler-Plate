package com.iions.done.feature.main.data

import com.iions.done.feature.main.data.model.BannersResponse
import com.iions.done.feature.main.data.model.CategoriesResponse

interface MainRepository {
    fun isUserLoggedIn(): Boolean
    suspend fun fetchCategoryList(): List<CategoriesResponse>?
    suspend fun fetchBannerList(): List<BannersResponse>?

    interface Local {
        fun isUserLoggedIn(): Boolean
        suspend fun fetchCategoryList(): List<CategoriesResponse>?
        suspend fun fetchBannerList(): List<BannersResponse>?
    }

    interface Remote {
    }
}