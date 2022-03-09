package com.iions.done.feature.auth.data.remote

import com.iions.done.feature.auth.data.AuthRepository
import com.iions.done.feature.auth.data.model.*
import com.iions.done.remote.ApiService
import com.iions.done.utils.notNullMapper
import javax.inject.Inject

class AuthRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository.Remote {
    override suspend fun loginWithPhone(username: String): List<LoginResponse> {
        val requestParams = mutableMapOf<String, Any>()
        requestParams["phone_number"] = username
        val remoteResponse = apiService.loginWithPhone(requestParams)
        return notNullMapper(remoteResponse)
    }

    override suspend fun requestPin(phoneNumber: String): RequestPinResponse {
        TODO("Not yet implemented")
    }

    override suspend fun requestResetPin(resetPinRequestModel: ResetPinRequestModel): ResetPinResponse {
        TODO("Not yet implemented")
    }

    override suspend fun logout(token: String): List<LogoutResponse> {
        return notNullMapper(apiService.logout(token))
    }

    override suspend fun verifyPinRequest(pin: String, phone: String): VerifyPinResponse {
        val requestParams = mutableMapOf<String, Any>()
        requestParams["phone_number"] = phone
        requestParams["otp"] = pin
        requestParams["device_name"] = android.os.Build.DEVICE
        val remoteResponse = apiService.verifyPin(requestParams)
        return notNullMapper(remoteResponse)
    }
}