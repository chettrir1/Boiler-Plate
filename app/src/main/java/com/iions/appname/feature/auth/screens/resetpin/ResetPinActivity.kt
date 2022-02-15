package com.iions.appname.feature.auth.screens.resetpin

import android.app.Activity
import android.content.Intent
import androidx.activity.viewModels
import com.iions.appname.R
import com.iions.appname.base.BaseActivity
import com.iions.appname.databinding.ActivityResetPinBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPinActivity : BaseActivity<ActivityResetPinBinding>() {

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, ResetPinActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }

    private val viewModel: ResetPinViewModel by viewModels()

    override fun layout() = R.layout.activity_reset_pin

    override fun initObservers() {
    }

}