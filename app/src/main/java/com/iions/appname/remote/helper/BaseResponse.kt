package com.iions.appname.remote.helper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(

    @SerializedName("message") @Expose var statusMessage: String? = null,
    @SerializedName("status") @Expose var status: Boolean? = false,
    @SerializedName("data") @Expose var response: T? = null
)