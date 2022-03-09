package com.iions.done.exceptions

import android.app.Activity
import android.content.Context
import android.database.sqlite.SQLiteException
import androidx.fragment.app.Fragment
import com.iions.done.R
import org.json.JSONException
import java.io.IOException
import java.net.UnknownHostException

class FailedResponseException(val status: Boolean, override val message: String) :
    IOException(message)

class NetworkNotAvailableException(val errorMessage: String) : IOException(errorMessage)
class DataNotAvailableException : IOException()

fun Activity.parseError(e: Throwable?): String {
    return when (e) {
        is JSONException -> getString(R.string.json_exception)
        is NetworkNotAvailableException -> getString(R.string.internet_conn_fail)
        is UnknownHostException -> getString(R.string.internet_conn_fail)
        is SQLiteException -> getString(R.string.db_not_available)
        is FailedResponseException -> e.message
        is DataNotAvailableException -> getString(R.string.data_not_available)
        is java.net.ConnectException -> "Please check your internet connection and try again."
        else -> getString(R.string.something_went_wrong)
    }
}

fun Fragment.parseError(e: Throwable?): String {
    return when (e) {
        is JSONException -> getString(R.string.json_exception)
        is NetworkNotAvailableException -> getString(R.string.internet_conn_fail)
        is UnknownHostException -> getString(R.string.internet_conn_fail)
        is SQLiteException -> getString(R.string.db_not_available)
        is FailedResponseException -> e.message
        is DataNotAvailableException -> getString(R.string.data_not_available)
        is java.net.ConnectException -> "Please check your internet connection and try again."
        else -> getString(R.string.something_went_wrong)
    }
}

fun Context.parseError(e: Throwable?): String {
    return when (e) {
        is JSONException -> getString(R.string.json_exception)
        is NetworkNotAvailableException -> getString(R.string.internet_conn_fail)
        is UnknownHostException -> getString(R.string.internet_conn_fail)
        is SQLiteException -> getString(R.string.db_not_available)
        is FailedResponseException -> e.message
        is DataNotAvailableException -> getString(R.string.data_not_available)
        is java.net.ConnectException -> "Please check your internet connection and try again."
        else -> getString(R.string.something_went_wrong)
    }
}