package com.iions.done.feature.summary.screens.confirm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityOrderConfirmedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderConfirmActivity : BaseActivity<ActivityOrderConfirmedBinding>() {
    private val viewModel: OrderConfirmViewModel by viewModels()

    override fun layout() = R.layout.activity_order_confirmed

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, OrderConfirmActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initObservers() {
    }

}