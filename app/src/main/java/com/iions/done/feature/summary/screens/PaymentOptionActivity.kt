package com.iions.done.feature.summary.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityPaymentOptionBinding
import com.iions.done.feature.groceries.screen.GroceryActivity

class PaymentOptionActivity : BaseActivity<ActivityPaymentOptionBinding>() {

    override fun layout() = R.layout.activity_payment_option

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, PaymentOptionActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.includeToolbar.tvTitle.text = getString(R.string.select_a_payment)

    }


    override fun initObservers() {
    }
}