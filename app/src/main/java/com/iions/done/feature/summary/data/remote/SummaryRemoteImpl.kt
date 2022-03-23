package com.iions.done.feature.summary.data.remote

import com.iions.done.exceptions.FailedResponseException
import com.iions.done.feature.main.data.model.CartBaseResponse
import com.iions.done.feature.summary.data.SummaryRepository
import com.iions.done.feature.summary.data.model.CreateOrderBaseResponse
import com.iions.done.remote.ApiService
import com.iions.done.utils.notNullMapper
import javax.inject.Inject

class SummaryRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : SummaryRepository.Remote {

    override suspend fun fetchCartList(authorizationToken: String): CartBaseResponse? {
        val remoteResponse= apiService.getCart(authorizationToken)
        if (remoteResponse.status==true){
            throw FailedResponseException(remoteResponse.status!!, remoteResponse.message.toString())
        }else{
            return remoteResponse.response
        }
    }

    override suspend fun createOrder(
        token: String,
        cod: String,
        districtId: Int?,
        streetId: Int?,
        localAddress: String?,
        addressId: Int?
    ): CreateOrderBaseResponse? {
        val requestParams = mutableMapOf<String, Any>()
        requestParams["payment_method"] = cod
        requestParams["district_id"] = districtId ?: -1
        requestParams["street_id"] = streetId ?: -1
        requestParams["local_address"] = localAddress ?: ""
        requestParams["address_id"] = addressId ?: -1
        val remoteResponse = apiService.createOrder(token, requestParams)
        if (remoteResponse.status==true){
            throw FailedResponseException(remoteResponse.status!!, remoteResponse.message.toString())
        }else{
            return remoteResponse.response
        }
    }
}