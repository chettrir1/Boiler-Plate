package com.iions.done.feature.auth.data

import com.iions.done.feature.auth.data.model.*

/**
 * Repository containing both Local and Remote methods
 * @see [UserLocalImpl] [UserRemoteImpl]
 */
interface AuthRepository {
    interface Local {
        fun getPhoneNumber(): String
        suspend fun clearPrefs()
        suspend fun saveUser(
            loginResponse: LoginResponse,
            password: String
        )

        fun getLoggedInUserId(): Int
    }

    interface Remote {
        suspend fun loginWithPhone(username: String): LoginResponse?
        suspend fun requestPin(phoneNumber: String): RequestPinResponse
        suspend fun requestResetPin(resetPinRequestModel: ResetPinRequestModel): ResetPinResponse
        suspend fun logout(token: String): LogoutResponse?
    }

    fun getPhoneNumber(): String
    suspend fun loginWithPhone(username: String): LoginResponse?
    suspend fun requestPin(phoneNumber: String): RequestPinResponse
    suspend fun requestResetPin(resetPinRequestModel: ResetPinRequestModel): ResetPinResponse
    fun getLoginUserId(): Int
}