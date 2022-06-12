package com.iions.done.feature.main.data.model

import com.google.gson.annotations.SerializedName
import com.iions.done.feature.auth.data.model.UserAddressResponse

data class ProfileBaseResponse(
    @SerializedName("user")
    var user: ProfileResponse?
)

data class ProfileResponse(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("email_verified_at")
    var emailVerifiedAt: String?,
    @SerializedName("phone_number")
    var phoneNumber: String?,
    @SerializedName("addresses")
    var addresses: List<UserAddressResponse>? = null
)



