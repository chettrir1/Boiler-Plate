package com.iions.done.remote.helper

import android.app.Activity
import android.content.Context
import com.iions.SharedPreferenceManager
import com.iions.done.exceptions.*
import com.iions.done.feature.auth.screens.login.smslogin.SmsLoginActivity
import com.iions.done.remote.Constants.AUTHENTICATION_ERROR_401
import com.iions.done.remote.Constants.FILE_NOT_FOUND
import com.iions.done.remote.Constants.GATE_WAY_TIME_OUT
import com.iions.done.remote.Constants.INTERNAL_SERVER_ERROR
import com.iions.done.remote.Constants.TOKEN_EXPIRED
import com.iions.done.remote.Constants.VALIDATION_ERROR
import com.iions.done.utils.checkNetworkAvailability
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

class ApiInterceptor @Inject constructor(
    val context: Context,
    private val prefs: SharedPreferenceManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!checkNetworkAvailability(context)) {
            throw NetworkNotAvailableException("No Internet Connection.")
        }
        val requestBuilder = chain.request().newBuilder()
        val response = chain.proceed(requestBuilder.build())
        val responseBody = response.body
        val responseString = responseBody?.string()

        when (response.code) {
            TOKEN_EXPIRED -> {
                SmsLoginActivity.start(context as Activity)
                throw FailedResponseException(true, "Token Expired!")
            }
            INTERNAL_SERVER_ERROR -> {
                throw FailedResponseException(true, "Internal Server Error!")
            }
            AUTHENTICATION_ERROR_401 -> {
                throw UnauthenticatedException(true, "Authentication Error!")
            }
            VALIDATION_ERROR -> {
                throw ValidationException(true, "Validation Error!")
            }
            GATE_WAY_TIME_OUT -> {
                throw FailedResponseException(true, "Time Out!")
            }
            FILE_NOT_FOUND -> {
                throw FileNotFoundException(true, "File Not Found!")
            }
            else -> return response.newBuilder()
                .body((responseString ?: "").toResponseBody())
                .build()
        }
    }
}