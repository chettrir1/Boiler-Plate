package com.iions.appname.feature.auth.data

import com.iions.appname.feature.auth.data.model.LoginResponse
import com.iions.appname.feature.auth.data.model.RequestPinResponse
import com.iions.appname.feature.auth.data.model.ResetPinRequestModel
import com.iions.appname.feature.auth.data.model.ResetPinResponse
import com.iions.appname.utils.SchedulersFactory
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: AuthRepository.Local,
    private val remoteRepository: AuthRepository.Remote
) : AuthRepository {

    override suspend fun authenticateUser(
        username: String,
        password: String,
        apkVersionName: String,
        firebaseToken: String?
    ): LoginResponse? {
        return withContext(schedulersFactory.io()) {
            localRepository.clearPrefs()
            val loginResponse =
                remoteRepository.authenticateUser(username, password, apkVersionName, firebaseToken)
            if (loginResponse != null) {
                localRepository.saveUser(loginResponse, password)
            }
            return@withContext loginResponse
        }
    }

    override fun getPhoneNumber(): String = localRepository.getPhoneNumber()

    override suspend fun requestPin(phoneNumber: String): RequestPinResponse {
        return withContext(schedulersFactory.io()) {
            return@withContext remoteRepository.requestPin(phoneNumber)
        }
    }

    override suspend fun requestResetPin(resetPinRequestModel: ResetPinRequestModel): ResetPinResponse {
        return withContext(schedulersFactory.io()) {
            return@withContext remoteRepository.requestResetPin(resetPinRequestModel)
        }
    }

    override fun getLoginUserId(): Int {
        return localRepository.getLoggedInUserId()
    }
}
