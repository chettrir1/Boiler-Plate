package com.iions.done.feature.summary.screens.confirm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityOrderConfirmedBinding
import com.iions.done.feature.main.screens.MainActivity
import com.iions.done.feature.summary.data.model.CreateOrderBaseResponse
import com.iions.done.feature.summary.screens.OrderSummaryAdapter
import com.iions.done.remote.Constants
import com.iions.done.utils.Commons
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderConfirmActivity : BaseActivity<ActivityOrderConfirmedBinding>() {
    private val viewModel: OrderConfirmViewModel by viewModels()

    override fun layout() = R.layout.activity_order_confirmed

    companion object {
        fun start(activity: Activity, response: CreateOrderBaseResponse) {
            val intent = Intent(activity, OrderConfirmActivity::class.java)
            intent.putExtra(Constants.ORDER_RESPONSE, response)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBundleData()

        binding.btnBackToHome.setOnClickListener {
            MainActivity.start(this)
        }
    }

    private fun getBundleData() {
        val data = intent.getSerializableExtra(Constants.ORDER_RESPONSE) as CreateOrderBaseResponse
        binding.rvOrderSummary.adapter =
            data.order.cart?.toMutableList()
                ?.let { response -> OrderSummaryAdapter(response) }
        binding.rvOrderSummary.hasFixedSize()
        binding.tvTotalAmount.text = "Rs. ${Commons.currencyFormatter(data.order.totalPrice ?: 0)}"
    }

    override fun initObservers() {
    }

    override fun onBackPressed() {
    }

}