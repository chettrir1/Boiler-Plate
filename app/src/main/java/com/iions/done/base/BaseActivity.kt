package com.iions.done.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.iions.done.R

@SuppressLint("CutPasteId")
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: T

    abstract fun layout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layout())
        initObservers()
    }

    abstract fun initObservers()

    fun <T : ViewDataBinding> showLoading(binding: T?, message: String?) {
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)?.visibility = View.VISIBLE
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<TextView>(R.id.txtLoading)?.text = message ?: getString(R.string.loading)
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.VISIBLE
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<Button>(R.id.btnAction)?.visibility = View.GONE
    }

    fun <T : ViewDataBinding> showData(binding: T?) {
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)?.visibility = View.GONE
    }

    fun <T : ViewDataBinding> showError(binding: T?, errorMessage: String) {
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)?.visibility = View.VISIBLE
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<TextView>(R.id.txtLoading)?.text = errorMessage
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.GONE
    }

    fun <T : ViewDataBinding> showErrorWithImage(
        binding: T?,
        errorMessage: String,
        @DrawableRes thumbnail: Int
    ) {
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)?.visibility = View.VISIBLE
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<TextView>(R.id.txtLoading)?.text = errorMessage
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.GONE
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.GONE
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<ImageView>(R.id.thumb)?.visibility = View.VISIBLE
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<ImageView>(R.id.thumb)?.setImageResource(thumbnail)
    }

    fun <T : ViewDataBinding> showActionableError(
        binding: T?,
        errorMessage: String,
        actionLabel: String,
        actionListener: () -> Unit
    ) {
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)?.visibility = View.VISIBLE
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<TextView>(R.id.txtLoading)?.text = errorMessage
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<Button>(R.id.btnAction)?.apply {
                text = actionLabel
                visibility = View.VISIBLE
                setOnClickListener {
                    actionListener()
                }
            }
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.GONE
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
    }
}