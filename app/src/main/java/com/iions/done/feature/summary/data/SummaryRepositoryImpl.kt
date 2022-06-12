package com.iions.done.feature.summary.data

import com.iions.done.feature.auth.data.model.AddressResponse
import com.iions.done.feature.main.data.model.CartBaseResponse
import com.iions.done.feature.main.data.model.DistrictResponse
import com.iions.done.feature.main.data.model.ProfileBaseResponse
import com.iions.done.feature.main.data.model.StreetResponse
import com.iions.done.feature.summary.data.model.CreateOrderBaseResponse
import com.iions.done.utils.SchedulersFactory
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SummaryRepositoryImpl @Inject constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: SummaryRepository.Local,
    private val remoteRepository: SummaryRepository.Remote
) : SummaryRepository {

    override suspend fun fetchCartList(): CartBaseResponse? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.fetchCartList(localRepository.getAuthorizationToken())
        }
    }

    override suspend fun fetchAddressList(): List<AddressResponse>? {
        return withContext(schedulersFactory.io()) {
            localRepository.fetchAddressList()
        }
    }

    override suspend fun fetchDistrictList(): List<DistrictResponse>? {
        return withContext(schedulersFactory.io()) {
            localRepository.fetchDistrictList()
        }
    }

    override suspend fun fetchStreetList(districtId: Int): List<StreetResponse>? {
        return withContext(schedulersFactory.io()) {
            localRepository.fetchStreetList(districtId)
        }
    }

    override suspend fun createOrder(
        cod: String,
        districtId: Int?,
        streetId: Int?,
        localAddress: String?,
        addressId: Int?
    ): CreateOrderBaseResponse? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.createOrder(
                localRepository.getAuthorizationToken(),
                cod,
                districtId,
                streetId,
                localAddress,
                addressId
            )
        }
    }

    override suspend fun fetchProfileResponse(): ProfileBaseResponse? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.fetchProfileResponse(localRepository.getAuthorizationToken())
        }
    }
}
