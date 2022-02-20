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
                image = "https://www.shaadidukaan.com/vogue/wp-content/uploads/2019/08/hug-kiss-images.jpg"
            )
        )
        items.add(
            CategoriesResponse(
                id = 1,
                name = "Grocery",
                image = "https://images.pexels.com/photos/1461974/pexels-photo-1461974.jpeg"
            )
        )
        items.add(
            CategoriesResponse(
                id = 1,
                name = "Travel",
                image = "https://cdn.pixabay.com/photo/2018/08/14/13/23/ocean-3605547__340.jpg"
            )
        )
        items.add(
            CategoriesResponse(
                id = 1,
                name = "Cosmetics",
                image = "https://static.toiimg.com/thumb/msid-31346158,width-748,height-499,resizemode=4,imgsize-114461/.jpg"
            )
        )
        items.add(
            CategoriesResponse(
                id = 1,
                name = "Electronics",
                image = "https://www.bhaktiphotos.com/wp-content/uploads/2018/04/Hindu-Shiva-God-Wallpaper-Free-Download.jpg"
            )
        )
        items.add(
            CategoriesResponse(
                id = 1,
                name = "Fashion",
                image = "https://www.filmibeat.com/ph-big/2020/02/v-2020_158253142110.jpg"
            )
        )
        return items
    }

    override suspend fun fetchBannerList(): List<BannersResponse>? {
        val packs = arrayListOf<BannersResponse>()
        packs.add(BannersResponse("https://cdn.dribbble.com/users/7361011/screenshots/15362916/food-banner---2_4x.jpg"))
        packs.add(BannersResponse("https://graphicsfamily.com/wp-content/uploads/edd/2020/11/Tasty-Food-Web-Banner-Design-scaled.jpg"))
        packs.add(BannersResponse("https://i.pinimg.com/736x/92/e3/0d/92e30ddc44098278a08add44b8403cc7.jpg"))
        packs.add(BannersResponse("https://img.freepik.com/free-psd/fast-food-restaurant-banner-template_23-2148987500.jpg?size=626&ext=jpg"))
        return packs
    }
}
