package com.iions.done.feature.auth.data.remote

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
        return remoteResponse.response
    }

//    override suspend fun authenticateUser(
//        username: String,
//        password: String,
//        apkVersionName: String,
//        firebaseToken: String?
//    ): LoginResponse? {
//        val requestParams = mutableMapOf<String, Any>()
//        requestParams["username"] = username
//        requestParams["password"] = password
//        val mobileDetails = mutableMapOf<String, Any>()
//        try {
//            mobileDetails["OS"] = System.getProperty("os.version") ?: ""
//            mobileDetails["API_LEVEL"] = android.os.Build.VERSION.SDK_INT
//            mobileDetails["BRAND"] = android.os.Build.BRAND
//            mobileDetails["DEVICE"] = android.os.Build.DEVICE
//            mobileDetails["MODEL"] = android.os.Build.MODEL
//            mobileDetails["PRODUCT"] = android.os.Build.PRODUCT
//            mobileDetails["VERSION_CODE"] = BuildConfig.VERSION_CODE
//            mobileDetails["VERSION_NAME"] = BuildConfig.VERSION_NAME
//        } catch (error: Exception) {
//            error.printStackTrace()
//        }
//        requestParams["mobile_details"] = mobileDetails
//        val remoteResponse = apiService.authenticateUser(requestParams)
//        return remoteResponse.response?.loginModel
//    }

    override suspend fun requestPin(phoneNumber: String): RequestPinResponse {
        TODO("Not yet implemented")
    }

    override suspend fun requestResetPin(resetPinRequestModel: ResetPinRequestModel): ResetPinResponse {
        TODO("Not yet implemented")
    }

    override suspend fun logout(token: String):  List<LogoutResponse>? {
        return apiService.logout(token).response
    }

    override suspend fun verifyPinRequest(pin: String, phone: String): VerifyPinResponse? {
        val requestParams = mutableMapOf<String, Any>()
        requestParams["phone_number"] = phone
        requestParams["otp"] = pin
        requestParams["device_name"] = android.os.Build.DEVICE
        val remoteResponse = apiService.verifyPin(requestParams)
        return remoteResponse.response
    }
}