package com.iions.done.feature.rating.data.local

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.rating.data.RatingRepository
import javax.inject.Inject

class RatingLocalImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val databaseManager: DatabaseManager
) : RatingRepository.Local {

    override fun isUserLoggedIn(): Boolean {
        return sharedPreferenceManager.username.isNotEmpty()
    }

    override fun getAuthorizationToken(): String {
        return sharedPreferenceManager.accessToken.toString()
    }
}
