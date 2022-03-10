package com.iions.done.feature.main.data.model

import com.google.gson.annotations.SerializedName

data class BannerResponse(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("image")
    var url: String?
)