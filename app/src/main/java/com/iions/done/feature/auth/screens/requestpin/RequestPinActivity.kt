package com.iions.done.feature.auth.screens.requestpin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityRequestPinBinding
import com.iions.done.feature.auth.screens.verifypin.VerifyPinActivity
import com.iions.done.utils.enablePianoEffect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestPinActivity : BaseActivity<ActivityRequestPinBinding>() {

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, RequestPinActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private val viewModel: RequestPinViewModel by viewModels()

    override fun layout() = R.layout.activity_request_pin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnContinue.enablePianoEffect().setOnClickListener {
            VerifyPinActivity.start(this, false)
        }
    }

    override fun initObservers() {
    }

}