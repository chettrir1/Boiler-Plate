package com.iions.done.feature.main.data.remote

import com.iions.done.feature.auth.data.model.LogoutResponse
import com.iions.done.feature.main.data.MainRepository
import com.iions.done.feature.main.data.model.CartBaseResponse
import com.iions.done.remote.ApiService
import com.iions.done.utils.notNullMapper
import javax.inject.Inject

class MainRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : MainRepository.Remote {

    override suspend fun requestLogout(token: String): List<LogoutResponse> {
        return notNullMapper(apiService.logout(token))
    }

    override suspend fun fetchCartList(token: String): CartBaseResponse {
        return notNullMapper(apiService.getCart(token))
    }
}