package com.iions.appname.feature.auth.screens.requestpin

import android.app.Activity
import android.content.Intent
import androidx.activity.viewModels
import com.iions.appname.R
import com.iions.appname.base.BaseActivity
import com.iions.appname.databinding.ActivityRequestPinBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestPinActivity : BaseActivity<ActivityRequestPinBinding>() {

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, RequestPinActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }

    private val viewModel: RequestPinViewModel by viewModels()

    override fun layout() = R.layout.activity_request_pin

    override fun initObservers() {
    }

}