package com.iions.done.feature.summary.data

import com.iions.done.feature.auth.data.model.AddressResponse
import com.iions.done.feature.main.data.model.CartBaseResponse
import com.iions.done.feature.main.data.model.DistrictResponse
import com.iions.done.feature.main.data.model.StreetResponse
import com.iions.done.feature.summary.data.model.CreateOrderBaseResponse

interface SummaryRepository {
    suspend fun fetchCartList(): CartBaseResponse?
    suspend fun fetchAddressList(): List<AddressResponse>?
    suspend fun fetchDistrictList(): List<DistrictResponse>?
    suspend fun fetchStreetList(districtId: Int): List<StreetResponse>?
    suspend fun createOrder(
        cod: String,
        districtId: Int?,
        streetId: Int?,
        localAddress: String?,
        addressId: Int?
    ): CreateOrderBaseResponse?

    interface Local {
        fun getAuthorizationToken(): String
        suspend fun fetchAddressList(): List<AddressResponse>?
        suspend fun fetchDistrictList(): List<DistrictResponse>?
        suspend fun fetchStreetList(districtId: Int): List<StreetResponse>?
    }

    interface Remote {
        suspend fun fetchCartList(authorizationToken: String): CartBaseResponse?
        suspend fun createOrder(
            token: String,
            cod: String,
            districtId: Int?,
            streetId: Int?,
            localAddress: String?,
            addressId: Int?
        ): CreateOrderBaseResponse?
    }
}