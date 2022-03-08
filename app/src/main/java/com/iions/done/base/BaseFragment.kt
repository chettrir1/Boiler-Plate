package com.iions.done.base

import android.annotation.SuppressLint
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.iions.done.R

@SuppressLint("CutPasteId")
abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    abstract fun layout(): Int

    protected var locationManager: LocationManager? = null

    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layout(), container, false)

        return binding.root
    }

    abstract fun initObservers()

    fun <T : ViewDataBinding> showLoading(binding: T?, message: String?) {
        val loadingLayout = binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
        loadingLayout?.visibility = View.VISIBLE
        loadingLayout?.findViewById<AppCompatTextView>(R.id.txtLoading)?.text =
            message ?: getString(R.string.please_wait)
        loadingLayout?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.VISIBLE
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<AppCompatButton>(R.id.btnAction)?.visibility = View.GONE
    }

    fun <T : ViewDataBinding> showData(binding: T?) {
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)?.visibility = View.GONE
    }

    fun <T : ViewDataBinding> showError(binding: T?, errorMessage: String) {
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)?.visibility =
            View.VISIBLE
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<AppCompatTextView>(R.id.txtLoading)?.text = errorMessage
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.GONE
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.GONE
    }

    fun <T : ViewDataBinding> showErrorWithImage(
        binding: T?,
        errorMessage: String,
        @DrawableRes thumbnail: Int
    ) {
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)?.visibility =
            View.VISIBLE
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<AppCompatTextView>(R.id.txtLoading)?.text = errorMessage
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.GONE
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.GONE
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<AppCompatImageView>(R.id.thumb)?.visibility = View.VISIBLE
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<AppCompatImageView>(R.id.thumb)?.setImageResource(thumbnail)
    }

    fun <T : ViewDataBinding> showActionableError(
        binding: T?,
        errorMessage: String,
        actionLabel: String,
        actionListener: () -> Unit
    ) {
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)?.visibility =
            View.VISIBLE
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<AppCompatTextView>(R.id.txtLoading)?.text = errorMessage
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<AppCompatButton>(R.id.btnAction)?.apply {
                text = actionLabel
                visibility = View.VISIBLE
                setOnClickListener {
                    actionListener()
                }
            }
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)
            ?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.GONE
    }
}