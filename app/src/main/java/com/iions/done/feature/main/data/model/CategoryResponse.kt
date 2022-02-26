package com.iions.done.feature.main.data.model

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("name")
    var name: String,
    @SerializedName("icon")
    var image: String
)