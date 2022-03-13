package com.iions.done.remote.helper

import android.content.Context
import com.iions.SharedPreferenceManager
import com.iions.done.exceptions.NetworkNotAvailableException
import com.iions.done.utils.checkNetworkAvailability
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ErrorInterceptor @Inject constructor(
    val context: Context,
    private val prefs: SharedPreferenceManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!checkNetworkAvailability(context)) {
            throw NetworkNotAvailableException("No Internet Connection.")
        }
        return try {
            chain.proceed(chain.request())
        } catch (e: Exception) {
            throw e
        }
    }
}