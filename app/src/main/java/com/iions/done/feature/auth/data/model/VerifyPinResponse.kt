package com.iions.done.feature.auth.data.model

import com.google.gson.annotations.SerializedName

data class VerifyPinResponse(
    @SerializedName("token")
    var token: String,
    @SerializedName("user")
    var user: UserResponse
)

data class UserResponse(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("email_verified_at")
    var emailVerifiedAt: String? = null,
    @SerializedName("pin")
    var pin: String? = null,
    @SerializedName("phone_number")
    var phoneNumber: String? = null,
    @SerializedName("addresses")
    var addresses: List<UserAddressResponse>? = null
)

data class UserAddressResponse(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("local_address")
    var localAddress: String? = null,
    @SerializedName("district")
    var district: DistrictResponse?,
    @SerializedName("street")
    var street: StreetResponse?
)

data class DistrictResponse(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String? = null
)

data class StreetResponse(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String? = null
)