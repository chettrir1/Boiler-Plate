package com.iions.done.feature.main.data.model

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("banners")
    var banners: List<BannerResponse>?,
    @SerializedName("modules")
    var modules: List<ModuleResponse>?,
    @SerializedName("grocery")
    var grocery: List<HomeGroceryRemoteResponse>?,
    @SerializedName("district")
    var district: List<DistrictResponse>?,
    @SerializedName("streets")
    var streets: List<StreetResponse>?
)