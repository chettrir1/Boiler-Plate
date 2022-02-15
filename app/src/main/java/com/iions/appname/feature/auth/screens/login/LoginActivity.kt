package com.iions.appname.feature.auth.screens.login

import android.app.Activity
import android.content.Intent
import androidx.activity.viewModels
import com.iions.appname.R
import com.iions.appname.base.BaseActivity
import com.iions.appname.databinding.ActivityLoginBinding
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

    override fun initObservers() {
    }

}