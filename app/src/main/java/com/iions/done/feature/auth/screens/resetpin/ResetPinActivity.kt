package com.iions.done.feature.auth.screens.resetpin

import android.app.Activity
import android.content.Intent
import androidx.activity.viewModels
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityResetPinBinding
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