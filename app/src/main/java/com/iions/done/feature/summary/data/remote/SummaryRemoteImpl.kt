package com.iions.done.feature.summary.data.remote

import com.iions.done.feature.main.data.model.CartBaseResponse
import com.iions.done.feature.summary.data.SummaryRepository
import com.iions.done.feature.summary.data.model.CreateOrderBaseResponse
import com.iions.done.remote.ApiService
import com.iions.done.utils.notNullMapper
import javax.inject.Inject

class SummaryRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : SummaryRepository.Remote {

    override suspend fun fetchCartList(authorizationToken: String): CartBaseResponse {
        return notNullMapper(apiService.getCart(authorizationToken))
    }

    override suspend fun createOrder(
        token: String,
        cod: String,
        districtId: Int,
        streetId: Int,
        localAddress: String
    ): CreateOrderBaseResponse {
        val requestParams = mutableMapOf<String, Any>()
        requestParams["payment_method"] = cod
        requestParams["district_id"] = districtId
        requestParams["street_id"] = streetId
        requestParams["local_address"] = localAddress
        val remoteResponse = apiService.createOrder(token, requestParams)
        return notNullMapper(remoteResponse)
    }
}