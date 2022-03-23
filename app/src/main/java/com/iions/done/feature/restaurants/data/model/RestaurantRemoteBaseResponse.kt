package com.iions.done.feature.restaurants.data.model

import com.google.gson.annotations.SerializedName

data class RestaurantDetailRemoteBaseResponse(
    @SerializedName("restaurant")
    var restaurant: RestaurantDetailResponse? = null
)

data class RestaurantMenuResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("image")
    var image: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("price")
    var price: Int? = 0
)

data class RestaurantDetailResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("logo")
    var logo: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("cover_image")
    var coverImage: String? = null,
    @SerializedName("address")
    var address: String?,
    @SerializedName("latitude")
    var latitude: Double? = null,
    @SerializedName("longitude")
    var longitude: Double? = null,
    @SerializedName("menu")
    var menu: List<RestaurantMenuResponse>? = null
)