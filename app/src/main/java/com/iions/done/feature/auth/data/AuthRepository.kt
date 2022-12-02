package com.iions.done.feature.auth.data

import com.iions.done.feature.auth.data.model.*

/**
 * Repository containing both Local and Remote methods
 * @see [UserLocalImpl] [UserRemoteImpl]
 */
interface AuthRepository {
    interface Local {
        suspend fun saveUser(loginResponse: LoginResponse)
    }

    interface Remote {
        suspend fun loginWithPhone(username: String): LoginResponse?

    }

    suspend fun loginWithPhone(username: String): LoginResponse?

}