package com.iions.done.feature.auth.screens.verifypin

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.iions.Constants
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityVerifyPinBinding
import com.iions.done.feature.auth.screens.resetpin.ResetPinActivity
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.enablePianoEffect
import com.iions.done.utils.progressdialog.ProgressDialog
import com.iions.done.utils.showToast
import com.iions.done.utils.startResendTimer
import com.iions.done.utils.stopResendTimer
import com.valdesekamdem.library.mdtoast.MDToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyPinActivity : BaseActivity<ActivityVerifyPinBinding>() {
    private val viewModel: VerifyPinViewModel by viewModels()

    private lateinit var dialog: Dialog

    companion object {
        fun start(activity: Activity, isNewUser: Boolean) {
            val intent = Intent(activity, VerifyPinActivity::class.java)
            intent.putExtra(Constants.IS_NEW_USER, isNewUser)
            activity.startActivity(intent)
            activity.finish()
        }
    }

    override fun layout() = R.layout.activity_verify_pin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startResendTimer(binding.btnResend)
        binding.btnVerify.enablePianoEffect().setOnClickListener {
            if (getBundle()) {
                val pin = binding.etPin.text.toString()
                if (pin.isEmpty()) {
                    showToast(
                        getString(R.string.verification_field_empty),
                        MDToast.TYPE_ERROR
                    )
                } else {
                    viewModel.verifyPinResponse(pin, "")
                }
            } else
                ResetPinActivity.start(this)
        }

        binding.btnResend.enablePianoEffect().setOnClickListener {
            viewModel.loginWithPhoneResponse()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopResendTimer()
    }

    private fun getBundle(): Boolean {
        return intent.getBooleanExtra(Constants.IS_NEW_USER, false)
    }

    override fun initObservers() {
        observeVerifyPinResponse()
        observeLoginResponse()
    }

    private fun observeVerifyPinResponse() {
        viewModel.verifyPinResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    showProgress()
                }
                Status.COMPLETE -> {
                    hideDialog()
                    onBackPressed()
                }
                Status.ERROR -> {
                    hideDialog()
                    showToast(
                        response.error?.message,
                        MDToast.TYPE_ERROR
                    )
                }
            }
        }
    }

    private fun observeLoginResponse() {
        viewModel.loginResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    showProgress()
                }
                Status.COMPLETE -> {
                    hideDialog()
                    showToast(
                        getString(R.string.verification_code_sent),
                        MDToast.TYPE_SUCCESS
                    )
                    startResendTimer(binding.btnResend)
                }
                Status.ERROR -> {
                    hideDialog()
                    showToast(
                        response.error?.message,
                        MDToast.TYPE_ERROR
                    )
                }
            }
        }
    }

    private fun showProgress() {
        dialog = ProgressDialog.progressDialog(this)
        dialog.show()
    }

    private fun hideDialog() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }
}