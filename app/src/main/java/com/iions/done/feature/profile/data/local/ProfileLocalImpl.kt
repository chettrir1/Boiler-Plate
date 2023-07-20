package com.iions.done.feature.profile.data.local

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.profile.data.ProfileRepository
import javax.inject.Inject

class ProfileLocalImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val databaseManager: DatabaseManager
) : ProfileRepository.Local {
    override fun getToken(): String {

    }
}