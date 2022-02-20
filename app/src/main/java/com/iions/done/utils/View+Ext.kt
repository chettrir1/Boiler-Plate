package com.iions.done.utils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.iions.done.R

fun View.makeRoundCorner(
    radius: Int,
    strokeWidth: Int? = null,
    @ColorInt strokeColor: Int? = null,
) {
    val gradientDrawable = GradientDrawable()
    gradientDrawable.setColor(Color.TRANSPARENT)
    gradientDrawable.cornerRadius = radius.toFloat()
    if (strokeWidth != null && strokeColor != null) gradientDrawable.setStroke(
        strokeWidth,
        strokeColor
    )
    background = gradientDrawable
}

fun <T : View> T.enablePianoEffect(): T {
    setOnTouchListener { v, event ->
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                scaleAnimate(true)
            }
            MotionEvent.ACTION_UP -> {
                scaleAnimate(false) {
                    v.performClick()
                }
            }
            MotionEvent.ACTION_CANCEL -> {
                scaleAnimate(false)
            }
        }

        true
    }
    return this
}

fun View.scaleAnimate(scale: Boolean, onEnd: (() -> Unit)? = null) {
    val targetScale = if (scale) 0.94f else 1f
    if (scaleX == targetScale && scaleY == targetScale) return
    val springScaleX = spring(
        SpringAnimation.SCALE_X,
        SpringForce.STIFFNESS_MEDIUM,
        SpringForce.DAMPING_RATIO_LOW_BOUNCY
    )
    val springScaleY = spring(
        SpringAnimation.SCALE_Y,
        SpringForce.STIFFNESS_MEDIUM,
        SpringForce.DAMPING_RATIO_LOW_BOUNCY
    )

    (getTag(R.id.tag_pending_end_listener) as? DynamicAnimation.OnAnimationEndListener)?.let {
        springScaleX.removeEndListener(it)
        springScaleY.removeEndListener(it)
    }

    if (!(scaleX == targetScale && scaleY == targetScale)) {
        val listener = object : DynamicAnimation.OnAnimationEndListener {
            override fun onAnimationEnd(
                animation: DynamicAnimation<out DynamicAnimation<*>>?,
                canceled: Boolean,
                value: Float,
                velocity: Float,
            ) {
                springScaleX.removeEndListener(this)
                springScaleX.removeEndListener(this)
                onEnd?.invoke()
            }
        }
        springScaleX.addEndListener(listener)
        springScaleX.addEndListener(listener)
        setTag(R.id.tag_pending_end_listener, listener)
    }

    springScaleX.animateToFinalPosition(targetScale)
    springScaleY.animateToFinalPosition(targetScale)
}

/**
 * An extension function which creates/retrieves a [SpringAnimation] and stores it in the [View]s
 * tag.
 */
fun View.spring(
    property: DynamicAnimation.ViewProperty,
    stiffness: Float = SpringForce.STIFFNESS_MEDIUM,
    damping: Float = SpringForce.DAMPING_RATIO_NO_BOUNCY,
    startVelocity: Float? = null,
): SpringAnimation {
    val key = getKey(property)
    var springAnim = getTag(key) as? SpringAnimation?
    if (springAnim == null) {
        springAnim = SpringAnimation(this, property)
        setTag(key, springAnim)
    }
    springAnim.spring = (springAnim.spring ?: SpringForce()).apply {
        this.dampingRatio = damping
        this.stiffness = stiffness
    }
    startVelocity?.let { springAnim.setStartVelocity(it) }
    return springAnim
}

/**
 * Map from a [ViewProperty] to an `id` suitable to use as a [View] tag.
 */
@IdRes
private fun getKey(property: DynamicAnimation.ViewProperty): Int {
    return when (property) {
        SpringAnimation.TRANSLATION_X -> R.id.translation_x
        SpringAnimation.TRANSLATION_Y -> R.id.translation_y
        SpringAnimation.TRANSLATION_Z -> R.id.translation_z
        SpringAnimation.SCALE_X -> R.id.scale_x
        SpringAnimation.SCALE_Y -> R.id.scale_y
        SpringAnimation.ROTATION -> R.id.rotation
        SpringAnimation.ROTATION_X -> R.id.rotation_x
        SpringAnimation.ROTATION_Y -> R.id.rotation_y
        SpringAnimation.X -> R.id.x
        SpringAnimation.Y -> R.id.y
        SpringAnimation.Z -> R.id.z
        SpringAnimation.ALPHA -> R.id.alpha
        SpringAnimation.SCROLL_X -> R.id.scroll_x
        SpringAnimation.SCROLL_Y -> R.id.scroll_y
        else -> throw IllegalAccessException("Unknown ViewProperty: $property")
    }
}
