package com.iions.done.feature.auth.screens.login.smslogin

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivitySmsLoginBinding
import com.iions.done.feature.auth.screens.login.LoginActivity
import com.iions.done.feature.auth.screens.verifypin.VerifyPinActivity
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.enablePianoEffect
import com.iions.done.utils.progressdialog.ProgressDialog
import com.iions.done.utils.showToast
import com.valdesekamdem.library.mdtoast.MDToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SmsLoginActivity : BaseActivity<ActivitySmsLoginBinding>() {
    private val viewModel: SmsLoginViewModel by viewModels()

    private lateinit var dialog: Dialog

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, SmsLoginActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun layout() = R.layout.activity_sms_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnLoginWithPassword.enablePianoEffect().setOnClickListener {
            LoginActivity.start(this)
        }

        binding.btnContinue.enablePianoEffect().setOnClickListener {
            binding.tilUsername.error = null
            val username = binding.etUsername.text.toString()
            if (username.isEmpty()) {
                binding.tilUsername.error = getString(R.string.username_field_empty)
            } else {
                viewModel.loginWithPhoneResponse(username)
            }
        }
    }

    override fun initObservers() {
        observeLoginResponse()
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
                    VerifyPinActivity.start(this, true)
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