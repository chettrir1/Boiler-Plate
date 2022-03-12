package com.iions.done.feature.auth.data.local

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.auth.data.AuthRepository
import com.iions.done.feature.auth.data.model.UserAddressResponse
import com.iions.done.feature.auth.data.model.VerifyPinResponse
import com.iions.done.feature.main.data.mapper.UserAddressMapper
import com.iions.done.utils.getCurrentDate
import javax.inject.Inject

class AuthLocalImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val databaseManager: DatabaseManager
) : AuthRepository.Local {

    override suspend fun saveUser(loginResponse: VerifyPinResponse) {
        sharedPreferenceManager.accessToken = "${"Bearer " + loginResponse.token}"
        sharedPreferenceManager.userId = loginResponse.user.id.toInt()
        sharedPreferenceManager.username = loginResponse.user.phoneNumber.toString()
        sharedPreferenceManager.phone = loginResponse.user.phoneNumber
        sharedPreferenceManager.name = loginResponse.user.name.toString()
        sharedPreferenceManager.email = loginResponse.user.email
        sharedPreferenceManager.loginDate = getCurrentDate()
        if (!loginResponse.user.addresses.isNullOrEmpty())
            saveAddress(loginResponse.user.addresses)
    }

    private suspend fun saveAddress(address: List<UserAddressResponse>?) {
        databaseManager.getUserAddressDao()
            .insert(UserAddressMapper.mapToLocal(address ?: emptyList()))
    }

    override suspend fun saveUsername(username: String) {
        sharedPreferenceManager.username = username
    }

    override fun getPhoneNumber(): String = sharedPreferenceManager.phone ?: ""

    override fun getLoggedInUserId(): String {
        return sharedPreferenceManager.username
    }
}
