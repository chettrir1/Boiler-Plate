package com.iions.done.feature.restaurants.data.model

import com.google.gson.annotations.SerializedName
import com.iions.done.feature.main.data.model.BannerResponse

data class RestaurantRemoteBaseResponse(
    @SerializedName("restaurants")
    var items: RestaurantItemsResponse? = null,
    @SerializedName("banner")
    var banner: List<BannerResponse>? = null
)

data class RestaurantItemsResponse(
    @SerializedName("current_page")
    var currentPage: Int,
    @SerializedName("last_page")
    var lastPage: Int,
    @SerializedName("data")
    var data: List<RestaurantResponse>? = null,
    @SerializedName("first_page_url")
    var firstPageUrl: String? = "",
    @SerializedName("from")
    var from: Int,
    @SerializedName("last_page_url")
    var lastPageUrl: String? = "",
    @SerializedName("next_page_url")
    var nextPageUrl: String? = "",
    @SerializedName("per_page")
    var perPage: Int? = 0,
    @SerializedName("total")
    var totalPage: Int? = 0
)

data class RestaurantResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("logo")
    var logo: String? = null,
    @SerializedName("description")
    var description: String,
    @SerializedName("cover_image")
    var coverImage: String?,
    @SerializedName("address")
    var address: String?,
    @SerializedName("latitude")
    var latitude: Double? = null,
    @SerializedName("longitude")
    var longitude: Double? = null
)