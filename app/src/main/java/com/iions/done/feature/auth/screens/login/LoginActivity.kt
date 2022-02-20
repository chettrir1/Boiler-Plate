package com.iions.done.feature.auth.screens.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityLoginBinding
import com.iions.done.feature.auth.screens.register.RegisterActivity
import com.iions.done.feature.auth.screens.requestpin.RequestPinActivity
import com.iions.done.feature.main.screens.MainActivity
import com.iions.done.utils.enablePianoEffect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }

    private val viewModel: LoginViewModel by viewModels()

    override fun layout() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvCreateAccount.setOnClickListener {
            RegisterActivity.start(this)
        }

        binding.btnContinue.enablePianoEffect().setOnClickListener {
            MainActivity.start(this)
        }

        binding.tvForgotPassword.setOnClickListener {
            RequestPinActivity.start(this)
        }
    }

    override fun initObservers() {
    }

}