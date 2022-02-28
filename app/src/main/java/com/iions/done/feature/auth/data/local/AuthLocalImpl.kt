package com.iions.done.feature.auth.data.local

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.auth.data.AuthRepository
import com.iions.done.feature.auth.data.model.VerifyPinResponse
import com.iions.done.utils.getCurrentDate
import javax.inject.Inject

class AuthLocalImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val databaseManager: DatabaseManager
) : AuthRepository.Local {

    override suspend fun saveUser(
        loginResponse: VerifyPinResponse
    ) {
        sharedPreferenceManager.accessToken = loginResponse.token
        sharedPreferenceManager.username = loginResponse.user.phoneNumber.toString()
        sharedPreferenceManager.phone = loginResponse.user.phoneNumber
        sharedPreferenceManager.name = loginResponse.user.name.toString()
        sharedPreferenceManager.email = loginResponse.user.email
        sharedPreferenceManager.loginDate = getCurrentDate()
    }

    override suspend fun saveUsername(username: String) {
        sharedPreferenceManager.username = username
    }

    override fun getPhoneNumber(): String = sharedPreferenceManager.phone ?: ""

    override suspend fun clearPrefs() {
        val phone = sharedPreferenceManager.phone
        sharedPreferenceManager.clearCache()
        sharedPreferenceManager.phone = phone
        databaseManager.getInstance().clearAllTables()
    }

    override fun getLoggedInUserId(): String {
        return sharedPreferenceManager.username
    }
}
