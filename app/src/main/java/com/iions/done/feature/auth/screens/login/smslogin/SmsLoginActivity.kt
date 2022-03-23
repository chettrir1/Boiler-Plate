package com.iions.done.feature.auth.screens.login.smslogin

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.iions.Constants.TYPE_LOGOUT
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
import kotlin.system.exitProcess

@AndroidEntryPoint
class SmsLoginActivity : BaseActivity<ActivitySmsLoginBinding>() {
    private val viewModel: SmsLoginViewModel by viewModels()

    private lateinit var dialog: Dialog

    private val isLogout: Boolean? by lazy {
        intent?.getBooleanExtra(TYPE_LOGOUT, false) ?: false
    }

    companion object {
        fun start(activity: Activity, isLogout: Boolean) {
            val intent = Intent(activity, SmsLoginActivity::class.java)
            intent.putExtra(TYPE_LOGOUT, isLogout)
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
                    hideProgress()
                    showToast(
                        getString(R.string.verification_code_sent),
                        MDToast.TYPE_SUCCESS
                    )
                    VerifyPinActivity.start(this, true)
                }
                Status.ERROR -> {
                    hideProgress()
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

    private fun hideProgress() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }

    override fun onBackPressed() {
        if (isLogout == true) {
            moveTaskToBack(true)
            android.os.Process.killProcess(android.os.Process.myPid())
            exitProcess(1)
        } else {
            super.onBackPressed()
        }
    }
}