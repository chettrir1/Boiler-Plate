package com.iions.done.feature.auth.data.remote

import com.iions.done.exceptions.FailedResponseException
import com.iions.done.feature.auth.data.AuthRepository
import com.iions.done.feature.auth.data.model.LoginResponse
import com.iions.done.remote.ApiService
import javax.inject.Inject

class AuthRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository.Remote {

    override suspend fun loginWithPhone(username: String): LoginResponse? {
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
}