package com.iions.done.utils.alertdialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.setPadding
import com.iions.done.R
import kotlinx.android.synthetic.main.view_clean_alert.view.*

class CleanAlertView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private var iconImage: Drawable? = null

    private var titleText: String? = null
        set(value) {
            field = value
            setTitle(value)
        }

    private var messageText: String? = null
        set(value) {
            field = value
            setTitle(value)
        }

    init {
        inflate(context, R.layout.view_clean_alert, this)
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
    }

    fun setIcon(image: Drawable?) {
        cleanIconImageView.setImageDrawable(image)
        cleanIconImageView.visibility = if (image == null) GONE else VISIBLE
    }

    fun setIconRes(imageRes: Int?) {
        if (imageRes != null) {
            cleanIconImageView.setImageResource(imageRes)
        }
        cleanIconImageView.visibility = if (imageRes == null) GONE else VISIBLE
    }

    fun setTitle(text: String?) {
        cleanTitleTextView.text = text
        val padding16 = 16.dpToPx()
        val padding4 = 4.dpToPx()
        if (text == null) {
            cleanTitleTextView.visibility = GONE
            cleanMessageTextView.setPadding(padding16, padding16, padding16, padding16)
        } else {
            cleanTitleTextView.visibility = VISIBLE
            cleanMessageTextView.setPadding(padding16, padding4, padding16, padding16)
        }
    }

    fun setMessage(text: String?) {
        cleanMessageTextView.text = text
        cleanMessageTextView.visibility = if (text == null) GONE else VISIBLE
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun addAction(title: String, handler: (() -> Unit)? = null) {
        val button = AppCompatButton(context)
        button.id = ViewCompat.generateViewId()
        button.setPadding(4.dpToPx())
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.0f)
        button.setOnClickListener {
            if (handler != null) {
                handler()
            }
        }
        button.text = title
        val outValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        button.setBackgroundResource(outValue.resourceId)
        button.setTextColor(Color.BLACK)
        button.elevation = 0f
        button.isAllCaps = false
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        cleanButtonContainerLayout.addView(button, layoutParams)
        cleanButtonContainerLayout.visibility = VISIBLE
    }

    fun clearActions() {
        cleanButtonContainerLayout.removeAllViews()
    }

}