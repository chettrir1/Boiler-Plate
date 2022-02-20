package com.iions.done.utils.alertdialog

import android.content.res.Resources
import kotlin.math.roundToInt

fun Int.spToPx(): Int {
    val fontScale = Resources.getSystem().displayMetrics.scaledDensity
    return (this * fontScale).roundToInt()
}

fun Int.dpToPx(): Int {
    val scale = Resources.getSystem().displayMetrics.density
    return (this * scale).roundToInt()
}

fun Int.pxToDp(): Int {
    val scale = Resources.getSystem().displayMetrics.density
    return (this / scale).roundToInt()
}