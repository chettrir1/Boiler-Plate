package com.iions.done.feature.auth.screens.register

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityRegisterBinding
import com.iions.done.feature.auth.screens.verifypin.VerifyPinActivity
import com.iions.done.utils.enablePianoEffect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, RegisterActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private val viewModel: RegisterViewModel by viewModels()

    override fun layout() = R.layout.activity_register

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvAlreadyHaveAccount.setOnClickListener {
            onBackPressed()
        }

        binding.btnRegister.enablePianoEffect().setOnClickListener {
            VerifyPinActivity.start(this)
        }
    }

    override fun initObservers() {
    }

}