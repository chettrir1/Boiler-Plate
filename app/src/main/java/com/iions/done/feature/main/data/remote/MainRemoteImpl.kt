package com.iions.done.feature.main.data.remote

import com.iions.done.exceptions.FailedResponseException
import com.iions.done.feature.auth.data.model.LogoutResponse
import com.iions.done.feature.main.data.MainRepository
import com.iions.done.feature.main.data.model.CartBaseResponse
import com.iions.done.feature.main.data.model.OrdersBaseResponse
import com.iions.done.feature.main.data.model.ProfileBaseResponse
import com.iions.done.feature.main.data.model.RemoveCartResponse
import com.iions.done.remote.ApiService
import javax.inject.Inject

class MainRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : MainRepository.Remote {

    override suspend fun requestLogout(token: String): LogoutResponse? {
        val remoteResponse = apiService.logout(token)
        if (remoteResponse.status == true) {
            throw FailedResponseException(
                remoteResponse.status!!,
                remoteResponse.message.toString()
            )
        } else {
            return remoteResponse.response
        }
    }

    override suspend fun fetchCartList(token: String): CartBaseResponse? {
        val remoteResponse = apiService.getCart(token)
        if (remoteResponse.status == true) {
            throw FailedResponseException(
                remoteResponse.status!!,
                remoteResponse.message.toString()
            )
        } else {
            return remoteResponse.response
        }
    }

    override suspend fun removeCartList(
        authorizationToken: String,
        cartId: Int
    ): RemoveCartResponse? {
        val requestParams = mutableMapOf<String, Any>()
        requestParams["cart_id"] = cartId
        val remoteResponse = apiService.removeFromCart(authorizationToken, requestParams)
        if (remoteResponse.status == true) {
            throw FailedResponseException(
                remoteResponse.status!!,
                remoteResponse.message.toString()
            )
        } else {
            return remoteResponse.response
        }
    }

    override suspend fun fetchProfileResponse(authorizationToken: String): ProfileBaseResponse? {
        val remoteResponse = apiService.fetchProfile(authorizationToken)
        if (remoteResponse.status == true) {
            throw FailedResponseException(
                remoteResponse.status!!,
                remoteResponse.message.toString()
            )
        } else {
            return remoteResponse.response
        }
    }

    override suspend fun fetchOrdersList(token: String): OrdersBaseResponse? {
        val remoteResponse = apiService.getOrders(token)
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