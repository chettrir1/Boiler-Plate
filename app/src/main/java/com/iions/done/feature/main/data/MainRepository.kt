package com.iions.done.feature.main.data

import com.iions.done.feature.main.data.model.BannerResponse
import com.iions.done.feature.main.data.model.CategoryResponse

interface MainRepository {
    fun isUserLoggedIn(): Boolean
    suspend fun fetchCategoryList(): List<CategoryResponse>?
    suspend fun fetchBannerList(): List<BannerResponse>?

    interface Local {
        fun isUserLoggedIn(): Boolean
        suspend fun fetchCategoryList(): List<CategoryResponse>?
        suspend fun fetchBannerList(): List<BannerResponse>?
    }

    interface Remote {
    }
}