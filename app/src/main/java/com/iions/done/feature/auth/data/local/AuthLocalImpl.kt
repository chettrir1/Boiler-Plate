package com.iions.done.feature.auth.data.local

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.auth.data.AuthRepository
import com.iions.done.feature.auth.data.model.LoginResponse
import com.iions.done.utils.getCurrentDate
import javax.inject.Inject

class AuthLocalImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val databaseManager: DatabaseManager
) : AuthRepository.Local {

    override suspend fun saveUser(
        loginResponse: LoginResponse,
        password: String
    ) {
//        sharedPreferenceManager.accessToken = loginResponse.token
//        sharedPreferenceManager.refreshToken = loginResponse.userModel.refreshToken
//        sharedPreferenceManager.userId = loginResponse.userModel.id
//        sharedPreferenceManager.phone = loginResponse.userModel.phoneNumber
//        sharedPreferenceManager.name = loginResponse.userModel.fullName
//        sharedPreferenceManager.email = loginResponse.userModel.email
//        sharedPreferenceManager.userRole = loginResponse.userModel.roleModel.name
//        sharedPreferenceManager.userRoleId = loginResponse.userModel.roleModel.id
//        sharedPreferenceManager.loginDate = getCurrentDate()
    }

    override fun getPhoneNumber(): String = sharedPreferenceManager.phone ?: ""

    override suspend fun clearPrefs() {
        val phone = sharedPreferenceManager.phone
        sharedPreferenceManager.clearCache()
        sharedPreferenceManager.phone = phone
        databaseManager.getInstance().clearAllTables()
    }

    override fun getLoggedInUserId(): Int {
        return sharedPreferenceManager.userId.toInt()
    }
}
