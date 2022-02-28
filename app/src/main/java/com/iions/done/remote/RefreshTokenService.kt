package com.iions.done.remote

import com.iions.SharedPreferenceManager
import com.iions.done.exceptions.FailedResponseException
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class RefreshTokenService @Inject constructor(var prefs: SharedPreferenceManager) {

    fun invalidateAccessToken(): String {
        val mOkHttpClient = OkHttpClient()
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("content-type", "application/json")
            .build()

        val request = Request.Builder()
            .url("" + "user/regenerate-refresh-token")
            .addHeader("RefreshToken", prefs.accessToken ?: "")
            .post(requestBody)
            .build()
        val httpResponse: Response = mOkHttpClient.newCall(request).execute()
        val refreshToken = httpResponse.headers["Authorization"]?.split(" ")?.get(1)

        if (!refreshToken.isNullOrEmpty()) {
            prefs.accessToken = refreshToken
            return refreshToken
        } else {
            throw FailedResponseException(httpResponse.code, "Error requesting new token")
        }
    }
}
