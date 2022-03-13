package com.iions.done.feature.auth.data.remote

import com.google.gson.Gson
import com.iions.done.exceptions.FailedResponseException
import com.iions.done.feature.auth.data.AuthRepository
import com.iions.done.feature.auth.data.model.*
import com.iions.done.remote.ApiService
import javax.inject.Inject

class AuthRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository.Remote {

    override suspend fun loginWithPhone(username: String): List<LoginResponse>? {
        val requestParams = mutableMapOf<String, Any>()
        requestParams["phone_number"] = username
        val remoteResponse = apiService.loginWithPhone(requestParams)
        if (remoteResponse.status == true) {
            throw FailedResponseException(
                remoteResponse.status!!,
                remoteResponse.message.toString()
            )
        } else {
            return remoteResponse.response
        }
    }

    override suspend fun requestPin(phoneNumber: String): RequestPinResponse {
        TODO("Not yet implemented")
    }

    override suspend fun requestResetPin(resetPinRequestModel: ResetPinRequestModel): ResetPinResponse {
        TODO("Not yet implemented")
    }

    override suspend fun verifyPinRequest(pin: String, phone: String): VerifyPinResponse? {
        val requestParams = mutableMapOf<String, Any>()
        requestParams["phone_number"] = phone
        requestParams["otp"] = pin
        requestParams["device_name"] = android.os.Build.DEVICE
        val remoteResponse = apiService.verifyPin(requestParams)
        if (remoteResponse.status == true) {
            throw FailedResponseException(
                remoteResponse.status!!,
                remoteResponse.message.toString()
            )
        } else {
            return remoteResponse.response
        }
    }
}