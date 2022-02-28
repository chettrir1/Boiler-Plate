package com.iions.done.feature.main.data.remote

import com.iions.done.feature.auth.data.model.LogoutResponse
import com.iions.done.feature.main.data.MainRepository
import com.iions.done.remote.ApiService
import javax.inject.Inject

class MainRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : MainRepository.Remote {

    override suspend fun requestLogout(token: String): LogoutResponse? {
        return apiService.logout(token).response
    }
}