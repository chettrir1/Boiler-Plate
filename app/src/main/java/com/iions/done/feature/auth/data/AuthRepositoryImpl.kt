package com.iions.done.feature.auth.data

import com.iions.done.feature.auth.data.model.*
import com.iions.done.utils.SchedulersFactory
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: AuthRepository.Local,
    private val remoteRepository: AuthRepository.Remote
) : AuthRepository {

//    override suspend fun authenticateUser(
//        username: String,
//        password: String,
//        apkVersionName: String,
//        firebaseToken: String?
//    ): LoginResponse? {
//        return withContext(schedulersFactory.io()) {
//            localRepository.clearPrefs()
//            val loginResponse =
//                remoteRepository.authenticateUser(username, password, apkVersionName, firebaseToken)
//            if (loginResponse != null) {
//                localRepository.saveUser(loginResponse, password)
//            }
//            return@withContext loginResponse
//        }
//    }

    override fun getPhoneNumber(): String = localRepository.getPhoneNumber()

    override suspend fun loginWithPhone(username: String): List<LoginResponse>? {
        return withContext(schedulersFactory.io()) {
            localRepository.saveUsername(username)
            remoteRepository.loginWithPhone(username)
        }
    }

    override suspend fun requestPin(phoneNumber: String): RequestPinResponse {
        return withContext(schedulersFactory.io()) {
            remoteRepository.requestPin(phoneNumber)
        }
    }

    override suspend fun requestResetPin(resetPinRequestModel: ResetPinRequestModel): ResetPinResponse {
        return withContext(schedulersFactory.io()) {
            remoteRepository.requestResetPin(resetPinRequestModel)
        }
    }

    override fun getLoginUserId(): String {
        return localRepository.getLoggedInUserId()
    }

    override suspend fun verifyPinRequest(pin: String, phone: String): VerifyPinResponse? {
        return withContext(schedulersFactory.io()) {
            val remoteResponse = remoteRepository.verifyPinRequest(pin, phone)
            if (remoteResponse != null) {
                localRepository.saveUser(remoteResponse)
            }
            remoteResponse
        }
    }
}
