package com.iions

import android.content.Context
import androidx.core.content.edit
import com.iions.appname.utils.SharedPreferenceLongLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceManager @Inject constructor(var context: Context) {

    private var sharedPreferenceLongLiveData: SharedPreferenceLongLiveData? = null

    var userId: Long
        get() = sharedPreferences.getLong(Constants.PREF_USER_ID, 0L)
        set(value) = sharedPreferences.edit { putLong(Constants.PREF_USER_ID, value) }

    var name: String
        get() = sharedPreferences.getString(Constants.PREF_USER_NAME, "") ?: ""
        set(value) = sharedPreferences.edit { putString(Constants.PREF_USER_NAME, value) }

    var phone: String?
        get() = sharedPreferences.getString(Constants.PREF_PHONE, "")
        set(value) = sharedPreferences.edit { putString(Constants.PREF_PHONE, value) }

    var email: String?
        get() = sharedPreferences.getString(Constants.PREF_EMAIL, "")
        set(value) = sharedPreferences.edit { putString(Constants.PREF_EMAIL, value) }

    private val sharedPreferences =
        context.getSharedPreferences(Constants.PREF_FILE, Context.MODE_PRIVATE)

    var accessToken: String?
        get() = sharedPreferences.getString(Constants.PREF_TOKEN, "")
        set(value) = sharedPreferences.edit { putString(Constants.PREF_TOKEN, value) }

    var refreshToken: String?
        get() = sharedPreferences.getString(Constants.PREF_REFRESH_TOKEN, "")
        set(value) = sharedPreferences.edit { putString(Constants.PREF_REFRESH_TOKEN, value) }

    var userRole: String
        get() = sharedPreferences.getString(Constants.PREF_ROLE, "") ?: ""
        set(value) = sharedPreferences.edit { putString(Constants.PREF_ROLE, value) }

    var userRoleId: Long
        get() = sharedPreferences.getLong(Constants.PREF_ROLE_ID, -1)
        set(value) = sharedPreferences.edit { putLong(Constants.PREF_ROLE_ID, value) }

    fun clearCache() {
        sharedPreferences.edit().clear().apply()
    }

    var loginDate: String
        get() = sharedPreferences.getString(Constants.PREF_LOGIN_DATE, "") ?: ""
        set(value) = sharedPreferences.edit().putString(Constants.PREF_LOGIN_DATE, value).apply()

}