package com.iions.done.feature.auth.data

import com.iions.done.feature.auth.data.model.LoginResponse
import com.iions.done.utils.SchedulersFactory
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: AuthRepository.Local,
    private val remoteRepository: AuthRepository.Remote
) : AuthRepository {

    override suspend fun loginWithPhone(username: String): LoginResponse? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.loginWithPhone(username)
        }
    }

}
