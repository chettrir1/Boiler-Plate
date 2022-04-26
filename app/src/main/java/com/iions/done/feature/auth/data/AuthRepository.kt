package com.iions.done.feature.auth.data

import com.iions.done.feature.auth.data.model.*

/**
 * Repository containing both Local and Remote methods
 * @see [UserLocalImpl] [UserRemoteImpl]
 */
interface AuthRepository {
    interface Local {
        fun getPhoneNumber(): String
        suspend fun saveUser(loginResponse: VerifyPinResponse)
        suspend fun saveUsername(username: String)
        fun getLoggedInUserId(): String
    }

    interface Remote {
        suspend fun loginWithPhone(username: String): LoginResponse?
        suspend fun requestPin(phoneNumber: String): RequestPinResponse
        suspend fun requestResetPin(resetPinRequestModel: ResetPinRequestModel): ResetPinResponse
        suspend fun verifyPinRequest(pin: String, phone: String, fcmToken: String): VerifyPinResponse?
    }

    fun getPhoneNumber(): String
    suspend fun loginWithPhone(username: String): LoginResponse?
    suspend fun requestPin(phoneNumber: String): RequestPinResponse
    suspend fun requestResetPin(resetPinRequestModel: ResetPinRequestModel): ResetPinResponse
    fun getLoginUserId(): String
    suspend fun verifyPinRequest(pin: String, phone: String, fcmToken: String): VerifyPinResponse?
}