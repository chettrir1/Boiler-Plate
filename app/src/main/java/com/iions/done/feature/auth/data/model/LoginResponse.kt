package com.iions.done.feature.auth.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class LoginBaseResponse(
    @SerializedName("login") var loginModel: LoginResponse
)

@Keep
class LoginResponse(
    @SerializedName("token") var token: String,
    @SerializedName("user") var userModel: User,
)

@Keep
data class User(
    @SerializedName("id") var id: Long,
    @SerializedName("fullName") var fullName: String,
    @SerializedName("email") var email: String,
    @SerializedName("phoneNumber") var phoneNumber: String,
    @SerializedName("firstName") var firstName: String,
    @SerializedName("lastName") var lastName: String,
    @SerializedName("refreshToken") var refreshToken: String?,
    @SerializedName("parentUserId") var parentUserId: Int,
    @SerializedName("role") var roleModel: Role,
)

@Keep
data class Role(
    @SerializedName("id") var id: Long,
    @SerializedName("name") var name: String
)