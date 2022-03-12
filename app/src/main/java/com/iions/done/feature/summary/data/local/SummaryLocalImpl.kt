package com.iions.done.feature.summary.data.local

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.auth.data.model.AddressResponse
import com.iions.done.feature.auth.data.model.UserAddressResponse
import com.iions.done.feature.main.data.model.DistrictResponse
import com.iions.done.feature.main.data.model.StreetResponse
import com.iions.done.feature.summary.data.SummaryRepository
import javax.inject.Inject

class SummaryLocalImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val databaseManager: DatabaseManager
) : SummaryRepository.Local {

    override fun getAuthorizationToken(): String {
        return sharedPreferenceManager.accessToken.toString()
    }

    override suspend fun fetchAddressList(): List<AddressResponse> {
        return databaseManager.getUserAddressDao().getUserAddressResponse()
    }

    override suspend fun fetchDistrictList(): List<DistrictResponse> {
        return databaseManager.getDistrictDao().getDistrictResponse()
    }

    override suspend fun fetchStreetList(districtId: Int): List<StreetResponse>? {
        return databaseManager.getStreetDao().getStreetResponse(districtId)
    }
}
