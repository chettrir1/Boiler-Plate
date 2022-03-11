package com.iions.done.feature.summary.data.remote

import com.iions.done.feature.main.data.model.CartBaseResponse
import com.iions.done.feature.summary.data.SummaryRepository
import com.iions.done.remote.ApiService
import com.iions.done.utils.notNullMapper
import javax.inject.Inject

class SummaryRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : SummaryRepository.Remote {

    override suspend fun fetchCartList(authorizationToken: String): CartBaseResponse {
        return notNullMapper(apiService.getCart(authorizationToken))
    }
}