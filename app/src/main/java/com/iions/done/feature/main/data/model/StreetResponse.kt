package com.iions.done.feature.main.data.model

import com.google.gson.annotations.SerializedName

data class StreetResponse(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("district_id")
    var districtId: Int?
)