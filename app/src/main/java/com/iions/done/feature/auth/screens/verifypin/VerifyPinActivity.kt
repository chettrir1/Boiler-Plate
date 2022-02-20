package com.iions.done.feature.auth.screens.verifypin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.iions.Constants
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityVerifyPinBinding
import com.iions.done.feature.auth.screens.resetpin.ResetPinActivity
import com.iions.done.feature.main.screens.MainActivity
import com.iions.done.utils.enablePianoEffect
import com.iions.done.utils.startResendTimer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyPinActivity : BaseActivity<ActivityVerifyPinBinding>() {

    companion object {
        fun start(activity: Activity, isNewUser: Boolean) {
            val intent = Intent(activity, VerifyPinActivity::class.java)
            intent.putExtra(Constants.IS_NEW_USER, isNewUser)
            activity.startActivity(intent)
        }
    }

    private val viewModel: VerifyPinViewModel by viewModels()

    override fun layout() = R.layout.activity_verify_pin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startResendTimer(binding.btnResend)
        binding.btnVerify.enablePianoEffect().setOnClickListener {
            if (getBundle())
                MainActivity.start(this)
            else
                ResetPinActivity.start(this)
        }
    }

    private fun getBundle(): Boolean {
        return intent.getBooleanExtra(Constants.IS_NEW_USER, false)
    }

    override fun initObservers() {
    }

}