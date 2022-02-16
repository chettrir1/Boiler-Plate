package com.iions.done.feature.main.data.local

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.main.data.MainRepository
import com.iions.done.feature.main.data.model.BannersResponse
import com.iions.done.feature.main.data.model.CategoriesResponse
import javax.inject.Inject

class MainLocalImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val databaseManager: DatabaseManager
) : MainRepository.Local {

    override fun isUserLoggedIn(): Boolean {
        return (sharedPreferenceManager.userId ?: 0) > 0
    }

    override suspend fun fetchCategoryList(): List<CategoriesResponse> {
        val items = arrayListOf<CategoriesResponse>()
        items.add(
            CategoriesResponse(
                id = 1,
                name = "Restaurants",
                image = "https://cdn.pixabay.com/photo/2017/10/04/09/56/laboratory-2815641_1280.jpg"
            )
        )
        items.add(
            CategoriesResponse(
                id = 1,
                name = "Grocery",
                image = "https://cdn.pixabay.com/photo/2017/10/04/09/56/laboratory-2815641_1280.jpg"
            )
        )
        items.add(
            CategoriesResponse(
                id = 1,
                name = "Travel",
                image = "https://cdn.pixabay.com/photo/2017/10/04/09/56/laboratory-2815641_1280.jpg"
            )
        )
        items.add(
            CategoriesResponse(
                id = 1,
                name = "Cosmetics",
                image = "https://cdn.pixabay.com/photo/2017/10/04/09/56/laboratory-2815641_1280.jpg"
            )
        )
        items.add(
            CategoriesResponse(
                id = 1,
                name = "Electronics",
                image = "https://cdn.pixabay.com/photo/2017/10/04/09/56/laboratory-2815641_1280.jpg"
            )
        )
        items.add(
            CategoriesResponse(
                id = 1,
                name = "Fashion",
                image = "https://cdn.pixabay.com/photo/2017/10/04/09/56/laboratory-2815641_1280.jpg"
            )
        )
        return items
    }

    override suspend fun fetchBannerList(): List<BannersResponse>? {
        val packs = arrayListOf<BannersResponse>()
        packs.add(BannersResponse("https://cdn.pixabay.com/photo/2017/10/04/09/56/laboratory-2815641_1280.jpg"))
        packs.add(BannersResponse("https://cdn.pixabay.com/photo/2017/10/04/09/56/laboratory-2815641_1280.jpg"))
        packs.add(BannersResponse("https://cdn.pixabay.com/photo/2017/10/04/09/56/laboratory-2815641_1280.jpg"))
        packs.add(BannersResponse("https://cdn.pixabay.com/photo/2017/10/04/09/56/laboratory-2815641_1280.jpg"))
        packs.add(BannersResponse("https://cdn.pixabay.com/photo/2017/10/04/09/56/laboratory-2815641_1280.jpg"))
        return packs
    }
}
