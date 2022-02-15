package com.iions.appname.feature.main.data.local

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.appname.feature.main.data.MainRepository
import javax.inject.Inject

class MainLocalImpl @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val databaseManager: DatabaseManager
) : MainRepository.Local {

    override fun isUserLoggedIn(): Boolean {
        return (sharedPreferenceManager.userId ?: 0) > 0
    }

}
