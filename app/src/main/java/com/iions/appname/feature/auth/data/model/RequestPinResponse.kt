package com.iions.appname.feature.auth.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class RequestPinResponse(
    @SerializedName("requestPasswordReset") var message: String
)