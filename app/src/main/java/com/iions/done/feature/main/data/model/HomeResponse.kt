package com.iions.done.feature.main.data.model

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("banners")
    var banners: List<BannerResponse>?,
    @SerializedName("modules")
    var modules: List<ModuleResponse>?,
    @SerializedName("grocery")
    var category: List<HomeGroceryRemoteResponse>?,
    @SerializedName("district")
    var district: List<DistrictResponse>?,
    @SerializedName("streets")
    var streets: List<StreetResponse>?,
    @SerializedName("restaurant")
    var restaurant: List<HomeRestaurantRemoteResponse>?
)