package com.iions.appname.feature.auth.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class ResetPinRequestModel(
    @SerializedName("verificationCode") var verificationCode: String,
    @SerializedName("password") var newPassword: String
)