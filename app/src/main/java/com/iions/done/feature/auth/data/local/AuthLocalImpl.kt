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

    override suspend fun saveUser(loginResponse: LoginResponse) {
        sharedPreferenceManager.accessToken = "${"Bearer " + loginResponse.token}"
        sharedPreferenceManager.userId = loginResponse.user.id.toInt()
        sharedPreferenceManager.username = loginResponse.user.phoneNumber.toString()
        sharedPreferenceManager.phone = loginResponse.user.phoneNumber
        sharedPreferenceManager.name = loginResponse.user.name.toString()
        sharedPreferenceManager.email = loginResponse.user.email
        sharedPreferenceManager.loginDate = getCurrentDate()
    }

}
