package com.iions.done.feature.auth.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class RequestPinResponse(
    @SerializedName("requestPasswordReset") var message: String
)