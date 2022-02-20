package com.iions.done.utils.alertdialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import com.iions.done.R

class CleanAlertDialog constructor(context: Context) : Dialog(context) {

    private val alertView: CleanAlertView by lazy { CleanAlertView(context) }

    init {
        setContentView(alertView)
        setCancelable(false)
        window?.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window?.attributes?.windowAnimations = R.style.CleanAlertDialogAnimation
    }

    fun setIcon(image: Drawable?): CleanAlertDialog {
        alertView.setIcon(image)
        return this
    }

    fun setIconRes(imageRes: Int?): CleanAlertDialog {
        alertView.setIconRes(imageRes)
        return this
    }

    fun setTitle(text: String?): CleanAlertDialog {
        alertView.setTitle(text)
        return this
    }

    fun setMessage(text: String?): CleanAlertDialog {
        alertView.setMessage(text)
        return this
    }

    fun setActions(buttons: Array<String>, handler: ((Int) -> Unit)? = null): CleanAlertDialog {
        alertView.clearActions()
        buttons.forEachIndexed { index, title ->
            alertView.addAction(title) { ->
                handler?.let { it(index) }
                dismiss()
            }
        }
        return this
    }

    fun setCancelOnTouchOutside(cancel: Boolean): CleanAlertDialog {
        setCanceledOnTouchOutside(cancel)
        return this
    }

    companion object {

        private lateinit var cleanAlertDialog: CleanAlertDialog

        fun shared(context: Context): CleanAlertDialog {
            if (!this::cleanAlertDialog.isInitialized || !cleanAlertDialog.isShowing) {
                cleanAlertDialog = CleanAlertDialog(context)
            }
            return cleanAlertDialog
        }

    }
}

