package com.iions.done.feature.auth.data.model

import com.google.gson.annotations.SerializedName

data class ResetPinResponse(
    @SerializedName("resetPassword") var message: String?
)