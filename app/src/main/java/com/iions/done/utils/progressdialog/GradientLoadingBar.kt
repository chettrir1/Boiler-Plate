package com.iions.done.utils.progressdialog

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.ProgressBar

class GradientLoadingBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ProgressBar(context, attrs, android.R.attr.progressBarStyleHorizontal) {

    private val scrollingGradient: ScrollingGradient

    init {
        val px =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 375f, resources.displayMetrics)
        val colors = intArrayOf(
            Color.parseColor("#4CD964"),
            Color.parseColor("#5AC8FA"),
            Color.parseColor("#007AFF"),
            Color.parseColor("#34AADC"),
            Color.parseColor("#5856D6"),
            Color.parseColor("#FF2D55")
        )
        this.scrollingGradient = ScrollingGradient(px, colors)

//        this.background = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors)
    }

    fun start() {
        this.isIndeterminate = true
        this.indeterminateDrawable = scrollingGradient
        this.visibility = VISIBLE
    }

    fun stop() {
        this.isIndeterminate = false
        this.indeterminateDrawable = null
        this.visibility = INVISIBLE
    }

}