package com.iions.done.feature.auth.screens.resetpin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityResetPinBinding
import com.iions.done.feature.auth.screens.login.LoginActivity
import com.iions.done.utils.enablePianoEffect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPinActivity : BaseActivity<ActivityResetPinBinding>() {

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, ResetPinActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private val viewModel: ResetPinViewModel by viewModels()

    override fun layout() = R.layout.activity_reset_pin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnContinue.enablePianoEffect().setOnClickListener {
            LoginActivity.start(this)
        }
    }

    override fun initObservers() {
    }

}