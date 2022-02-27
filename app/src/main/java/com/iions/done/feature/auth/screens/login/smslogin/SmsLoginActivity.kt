package com.iions.done.feature.auth.screens.login.smslogin

import android.app.Activity
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SmsLoginActivity : BaseActivity<ActivitySmsLoginBinding>() {

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, SmsLoginActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }

    private val viewModel: SmsLoginViewModel by viewModels()

    override fun layout() = R.layout.activity_sms_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvLoginWithPassword.setOnClickListener {
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
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        VerifyPinActivity.start(this, false)
                    }
                }
                Status.ERROR -> {
                }
            }
        }
    }
}