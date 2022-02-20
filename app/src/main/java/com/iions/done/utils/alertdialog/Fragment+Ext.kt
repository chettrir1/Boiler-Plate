package com.iions.done.utils.alertdialog

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import androidx.fragment.app.Fragment


sealed class ErrorMode {
    object Alert : ErrorMode()
    object SnackBar : ErrorMode()
    object NotificationBar : ErrorMode()
}

fun Fragment.openSettings() {
    startActivityForResult(Intent(Settings.ACTION_SETTINGS), 0)
}

fun Activity.openSettings() {
    startActivityForResult(Intent(Settings.ACTION_SETTINGS), 0)
}

fun Activity.openPermission() {
    Intent(ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:${this.packageName}")).apply {
        addCategory(Intent.CATEGORY_DEFAULT)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(this)
    }
}

fun Fragment.openPlayStore() {
    TODO()
}

fun Activity.openPlayStore() {
    val packageName = this.packageName
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
    intent.flags =
        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NO_ANIMATION
    startActivity(intent)
}

fun Fragment.showAlert(
    title: String? = null,
    message: String? = null,
    imageRes: Int? = null,
    buttons: Array<String> = arrayOf(),
    handler: ((Int) -> Unit)? = null
) {
    CleanAlertDialog.shared(requireContext())
        .setTitle(title)
        .setMessage(message)
        .setIconRes(imageRes)
        .setActions(buttons, handler)
        .show()
}

fun Activity.showAlert(
    title: String? = null,
    message: String? = null,
    imageRes: Int? = null,
    buttons: Array<String> = arrayOf(),
    handler: ((Int) -> Unit)? = null
) {
    CleanAlertDialog.shared(this)
        .setTitle(title)
        .setMessage(message)
        .setIconRes(imageRes)
        .setActions(buttons, handler)
        .show()
}