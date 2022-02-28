package com.iions

import android.content.Context
import androidx.core.content.edit
import com.iions.done.utils.SharedPreferenceLongLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceManager @Inject constructor(var context: Context) {

    private var sharedPreferenceLongLiveData: SharedPreferenceLongLiveData? = null

    private val sharedPreferences =
        context.getSharedPreferences(Constants.PREF_FILE, Context.MODE_PRIVATE)

    var username: String
        get() = sharedPreferences.getString(Constants.PREF_USER_NAME, "") ?: ""
        set(value) = sharedPreferences.edit { putString(Constants.PREF_USER_NAME, value) }

    var name: String
        get() = sharedPreferences.getString(Constants.PREF_NAME, "") ?: ""
        set(value) = sharedPreferences.edit { putString(Constants.PREF_NAME, value) }

    var phone: String?
        get() = sharedPreferences.getString(Constants.PREF_PHONE, "")
        set(value) = sharedPreferences.edit { putString(Constants.PREF_PHONE, value) }

    var email: String?
        get() = sharedPreferences.getString(Constants.PREF_EMAIL, "")
        set(value) = sharedPreferences.edit { putString(Constants.PREF_EMAIL, value) }

    var accessToken: String?
        get() = sharedPreferences.getString(Constants.PREF_TOKEN, "")
        set(value) = sharedPreferences.edit { putString(Constants.PREF_TOKEN, value) }

    fun clearCache() {
        sharedPreferences.edit().clear().apply()
    }

    var loginDate: String
        get() = sharedPreferences.getString(Constants.PREF_LOGIN_DATE, "") ?: ""
        set(value) = sharedPreferences.edit().putString(Constants.PREF_LOGIN_DATE, value).apply()

}