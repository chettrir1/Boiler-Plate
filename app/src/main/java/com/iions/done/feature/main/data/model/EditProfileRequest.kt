package com.iions.done.feature.main.data.model

import com.google.gson.annotations.SerializedName

data class EditProfileRequest(
    @SerializedName("name")
    val name: String? = null
)