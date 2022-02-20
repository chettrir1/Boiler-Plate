package com.iions.done.utils

import android.annotation.SuppressLint
import android.os.CountDownTimer
import androidx.appcompat.widget.AppCompatButton

private var countDownTimer: CountDownTimer? = null

fun startResendTimer(view: AppCompatButton) {
    countDownTimer = object : CountDownTimer(60000, 1000) {

        @SuppressLint("SetTextI18n")
        override fun onTick(time: Long) {
            view.text = "RESEND (${time.formatTime()}s)"
            view.isClickable = false
        }

        @SuppressLint("SetTextI18n")
        override fun onFinish() {
            view.text = "RESEND"
            view.isClickable = true
        }
    }.start()
}

fun stopResendTimer() {
    countDownTimer?.cancel()
}

fun Long.formatTime(): String {
    var seconds = this / 1000
    var minutes = seconds / 60
    seconds %= 60
    minutes %= 60
    var sec = seconds.toString()
    var min = minutes.toString()
    if (seconds < 10) sec = "0$seconds"
    return sec
}
