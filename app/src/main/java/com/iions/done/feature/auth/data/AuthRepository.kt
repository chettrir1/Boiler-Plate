package com.iions.done.feature.auth.data

import com.iions.done.feature.auth.data.model.LoginResponse
import com.iions.done.feature.auth.data.model.RequestPinResponse
import com.iions.done.feature.auth.data.model.ResetPinRequestModel
import com.iions.done.feature.auth.data.model.ResetPinResponse

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
//        suspend fun authenticateUser(
//            username: String,
//            password: String,
//            apkVersionName: String,
//            firebaseToken: String?
//        ): LoginResponse?

        suspend fun loginWithPhone(username: String): LoginResponse?
        suspend fun requestPin(phoneNumber: String): RequestPinResponse
        suspend fun requestResetPin(resetPinRequestModel: ResetPinRequestModel): ResetPinResponse
    }

    fun getPhoneNumber(): String
//    suspend fun authenticateUser(
//        username: String,
//        password: String,
//        apkVersionName: String,
//        firebaseToken: String?
//    ): LoginResponse?

    suspend fun loginWithPhone(username: String): LoginResponse?
    suspend fun requestPin(phoneNumber: String): RequestPinResponse
    suspend fun requestResetPin(resetPinRequestModel: ResetPinRequestModel): ResetPinResponse
    fun getLoginUserId(): Int
}