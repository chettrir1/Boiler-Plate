package com.iions.done.remote.helper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorResponse<T>(
    @SerializedName("message") @Expose var message: String? = null,
    @SerializedName("error") @Expose var response: T? = null
)
