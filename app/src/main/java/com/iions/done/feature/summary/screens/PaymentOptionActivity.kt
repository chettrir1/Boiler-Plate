package com.iions.done.feature.summary.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityPaymentOptionBinding
import com.iions.done.feature.groceries.screen.GroceryActivity
import com.iions.done.feature.summary.screens.address.ChooseAddressActivity
import com.iions.done.utils.Commons
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.enablePianoEffect
import com.iions.done.utils.showToast
import com.valdesekamdem.library.mdtoast.MDToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentOptionActivity : BaseActivity<ActivityPaymentOptionBinding>() {
    private val viewModel: PaymentOptionViewModel by viewModels()
    private var total = 0

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
        viewModel.fetchCartList()

        binding.includePaymentOption.llCashOnDelivery.isSelected = true
        binding.includePaymentOption.llDebitCard.enablePianoEffect().setOnClickListener {
            showToast(
                getString(R.string.feature_not_available_at_the_moment),
                MDToast.TYPE_INFO
            )
        }
        binding.includePaymentOption.llWallet.enablePianoEffect().setOnClickListener {
            showToast(
                getString(R.string.feature_not_available_at_the_moment),
                MDToast.TYPE_INFO
            )
        }

        binding.includeConfirmPayment.btnConfirmPayment.enablePianoEffect().setOnClickListener {
            ChooseAddressActivity.start(this)
        }

    }

    override fun initObservers() {
        observeCartResponse()
    }

    private fun observeCartResponse() {
        viewModel.cartResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    super.showLoading(binding.loadingLayout, getString(R.string.please_wait))
                }
                Status.COMPLETE -> {
                    response.data?.let { data ->
                        if (data.cart?.isEmpty() == false) {
                            data.cart?.forEach { cart ->
                                val price =
                                    cart.quantity?.let { quantity -> cart.price?.times(quantity) }
                                total += price!!
                            }
                            binding.rvOrderSummary.adapter =
                                data.cart?.toMutableList()
                                    ?.let { response -> OrderSummaryAdapter(response) }
                            binding.rvOrderSummary.hasFixedSize()
                            binding.tvTotalAmount.text = "Rs. ${Commons.currencyFormatter(total)}"
                            binding.includeConfirmPayment.tvTotalAmount.text =
                                "Rs. ${Commons.currencyFormatter(total)}"
                            super.showData(binding.loadingLayout)
                        } else {
                            super.showActionableError(
                                binding.loadingLayout,
                                errorMessage = getString(R.string.no_item_in_your_cart),
                                R.drawable.vc_empty,
                                actionLabel = getString(R.string.browse)
                            ) {
                                if (it == getString(R.string.browse)) {
                                    GroceryActivity.start(this)
                                }
                            }
                        }
                    }
                }
                Status.ERROR -> {
                    super.showActionableError(
                        binding.loadingLayout,
                        errorMessage = response.error?.message.toString(),
                        R.drawable.ic_error_cart,
                        actionLabel = getString(R.string.retry)
                    ) {
                        viewModel.fetchCartList()
                    }
                }
            }
        }
    }
}