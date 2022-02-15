package com.iions.appname.feature.auth.screens.register

import android.app.Activity
import android.content.Intent
import androidx.activity.viewModels
import com.iions.appname.R
import com.iions.appname.base.BaseActivity
import com.iions.appname.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, RegisterActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }

    private val viewModel: RegisterViewModel by viewModels()

    override fun layout() = R.layout.activity_register

    override fun initObservers() {
    }

}