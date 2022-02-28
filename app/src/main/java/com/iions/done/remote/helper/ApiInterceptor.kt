package com.iions.done.remote.helper

import android.content.Context
import android.util.Log
import com.iions.SharedPreferenceManager
import com.iions.done.exceptions.FailedResponseException
import com.iions.done.exceptions.NetworkNotAvailableException
import com.iions.done.remote.Constants.AUTHENCATION_ERROR_401
import com.iions.done.remote.Constants.GATE_WAY_TIME_OUT
import com.iions.done.remote.Constants.INTERNAL_SERVER_ERROR
import com.iions.done.remote.Constants.TOKEN_EXPIRED
import com.iions.done.remote.RefreshTokenService
import com.iions.done.utils.checkNetworkAvailability
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import javax.inject.Inject

class ApiInterceptor @Inject constructor(
    val context: Context,
    private val prefs: SharedPreferenceManager,
    private val refreshTokenService: RefreshTokenService
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!checkNetworkAvailability(context)) {
            throw NetworkNotAvailableException("No Internet Connection.")
        }

        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Connection", "Close")

        if (!prefs.accessToken.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", prefs.accessToken!!)
        }

        val response = chain.proceed(requestBuilder.build())
        val responseBody = response.body
        val responseString = responseBody?.string()
        if (prefs.accessToken.isNullOrEmpty()) {
            val accessToken = response.headers["Authorization"]?.split(" ")?.get(1)
            prefs.accessToken = accessToken
        }
        prefs.accessToken = response.headers["RefreshToken"]?.split(" ")?.get(1)

        when (response.code) {
            TOKEN_EXPIRED -> {
//                val intent = LoginActivity.getIntent(context as Activity)
//                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
//                context.startActivity(intent)
                throw FailedResponseException(403, "Session Error. Try again")
            }
            INTERNAL_SERVER_ERROR -> {
                throw FailedResponseException(response.code, "500 Internal Server Error")
            }
            GATE_WAY_TIME_OUT -> {
                throw FailedResponseException(response.code, "Time out!")
            }
            else -> return if (!prefs.accessToken.isNullOrEmpty() && (response.code == TOKEN_EXPIRED || response.code == AUTHENCATION_ERROR_401)) {
                val newToken = refreshTokenService.invalidateAccessToken()
                requestBuilder.removeHeader("Authorization")
                requestBuilder.addHeader("Authorization", newToken)
                chain.proceed(requestBuilder.build())
            } else {
                checkIfThereIsError(response, responseString)
                val contentType = responseBody?.contentType()
                response.newBuilder().body((responseString ?: "").toResponseBody(contentType))
                    .build()
            }
        }
    }

    private fun checkIfThereIsError(response: Response?, responseString: String?) {
        if (response?.code != 200) {
            if (responseString != null) {
                Log.e("Network response", responseString)
                val resObj: String?
                try {
                    resObj = JSONObject(responseString).getString("message")
                } catch (e: Exception) {
                    throw FailedResponseException(500, "Internal Server Error")
                }
                throw FailedResponseException(500, resObj)
            } else {
                throw FailedResponseException(500, "Internal Server Error")
            }
        }
    }
}