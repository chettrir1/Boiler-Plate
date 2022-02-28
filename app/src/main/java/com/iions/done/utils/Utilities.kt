package com.iions.done.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.Gravity
import androidx.fragment.app.Fragment
import com.valdesekamdem.library.mdtoast.MDToast
import java.text.SimpleDateFormat
import java.util.*

fun checkNetworkAvailability(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                )
            ) {
                return true
            }
        }
        return false
    } else {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}

inline fun <R> R?.orElse(block: () -> R): R {
    return this ?: block()
}

fun getCurrentDate(): String {
    val cal = Calendar.getInstance()
    cal.add(Calendar.DATE, 0)
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return simpleDateFormat.format(cal.time)
}

fun isValidContext(context: Context?): Boolean {
    if (context == null) {
        return false
    }
    if (context is Activity) {
        val activity: Activity = context
        !activity.isDestroyed && !activity.isFinishing
    }
    return true
}

fun Activity.showToast(message: String?, type: Int, duration: Int? = null) {
    if (isValidContext(this)) {
        val toast = MDToast.makeText(this, message, duration ?: MDToast.LENGTH_SHORT, type)
        toast.setGravity(Gravity.TOP, 0, 100)
        toast.show()
    }
}

fun Fragment.showToast(message: String?, type: Int, duration: Int? = null) {
    if (isValidContext(context)) {
        val toast = MDToast.makeText(context, message, duration ?: MDToast.LENGTH_SHORT, type)
        toast.setGravity(Gravity.TOP, 0, 100)
        toast.show()
    }
}