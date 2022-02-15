package com.iions.done.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.visible(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun SharedPreferences.Editor.putIntegerList(
    key: String,
    list: List<Int>?
): SharedPreferences.Editor {
    putString(key, list?.joinToString(",") ?: "")
    return this
}

fun SharedPreferences.getIntegerList(key: String, defValue: List<Int>?): List<Int>? {
    val value = getString(key, null)
    if (value.isNullOrBlank())
        return defValue
    return value.split(",").map { it.toInt() } ?: listOf()
}

fun View.showKeyboard() {
    (this.context as? Activity)?.showKeyboard()
}

fun View.hideKeyboard() {
    (this.context as? Activity)?.hideKeyboard()
}

fun Fragment.showKeyboard() {
    activity?.showKeyboard()
}

fun Fragment.hideKeyboard() {
    activity?.hideKeyboard()
}

fun Context.showKeyboard() {
    (this as? Activity)?.showKeyboard()
}

fun Context.hideKeyboard() {
    (this as? Activity)?.hideKeyboard()
}

private fun Activity.showKeyboard() {
    WindowInsetsControllerCompat(window, window.decorView).show(WindowInsetsCompat.Type.ime())
}

private fun Activity.hideKeyboard() {
    WindowInsetsControllerCompat(window, window.decorView).hide(WindowInsetsCompat.Type.ime())
}

fun Fragment.handleBackButtonEvent(
    onBackPressed: OnBackPressedCallback.() -> Unit
) {
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
        onBackPressed()
    }
}