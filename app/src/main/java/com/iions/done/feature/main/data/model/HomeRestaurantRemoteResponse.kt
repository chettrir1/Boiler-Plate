package com.iions.done.feature.main.data.model

import com.google.gson.annotations.SerializedName

data class HomeRestaurantRemoteResponse(
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("logo")
    var logo: String? = null,
    @SerializedName("cover_image")
    var coverImage: String? = null,
    @SerializedName("address")
    var address: String? = "",
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("latitude")
    var latitude: String? = null,
    @SerializedName("longitude")
    var longitude: String? = null,
)