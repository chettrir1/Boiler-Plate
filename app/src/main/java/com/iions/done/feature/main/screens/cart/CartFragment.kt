package com.iions.done.feature.main.screens.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iions.done.R
import com.iions.done.base.BaseFragment
import com.iions.done.databinding.FragmentCartBinding
import com.iions.done.feature.auth.screens.login.smslogin.SmsLoginActivity
import com.iions.done.feature.groceries.screen.GroceryActivity
import com.iions.done.feature.summary.screens.PaymentOptionActivity
import com.iions.done.utils.Commons
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.enablePianoEffect
import com.iions.done.utils.gone
import com.iions.done.utils.showToast
import com.valdesekamdem.library.mdtoast.MDToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>() {
    private val viewModel: CartViewModel by viewModels()
    private var total = 0

    override fun layout(): Int = R.layout.fragment_cart

    companion object {
        fun getInstance(): Fragment {
            return CartFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.includeProceedToCheckout.btnProceedToPayment.enablePianoEffect()
            .setOnClickListener {
                PaymentOptionActivity.start(requireActivity())
            }
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.isUserLoggedIn()) {
            viewModel.fetchCartList()
        } else {
            super.showActionableError(
                binding.loadingLayout,
                errorMessage = getString(R.string.you_havent_logged_in_yet),
                R.drawable.vc_cart,
                actionLabel = getString(R.string.login)
            ) {
                SmsLoginActivity.start(requireActivity(), false)
            }
        }
    }


    override fun initObservers() {
        observeCartResponse()
        observeRemoveCartResponse()
    }

    private fun observeCartResponse() {
        viewModel.cartResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    total = 0
                    binding.loadingLayout.thumb.gone()
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
                            binding.rvCart.adapter =
                                data.cart?.toMutableList()
                                    ?.let { response ->
                                        CartListAdapter(response) { item, delete ->
                                            if (delete) {
                                                viewModel.removeCartList(item.id ?: -1)
                                            }
                                        }
                                    }
                            binding.rvCart.hasFixedSize()
                            binding.includeProceedToCheckout.tvTotalAmount.text =
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
                                    GroceryActivity.start(requireActivity())
                                }
                            }
                        }
                    }
                }
                Status.ERROR -> {
                    super.showActionableError(
                        binding.loadingLayout,
                        errorMessage = if (response.error?.message.toString()
                                .contains("Authentication")
                        ) {
                            getString(R.string.you_havent_logged_in_yet)
                        } else {
                            response.error?.message.toString()
                        },
                        R.drawable.vc_cart,
                        actionLabel = if (response.error?.message.toString()
                                .contains("Authentication")
                        ) {
                            getString(R.string.login)
                        } else {
                            getString(R.string.retry)
                        }
                    ) {
                        if (it == getString(R.string.login)) {
                            SmsLoginActivity.start(requireActivity(), false)
                        } else {
                            viewModel.fetchCartList()
                        }
                    }
                }
            }
        }
    }

    private fun observeRemoveCartResponse() {
        viewModel.removeCartResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                }
                Status.COMPLETE -> {
                    showToast(
                        getString(R.string.item_removed_from_cart),
                        MDToast.TYPE_SUCCESS
                    )
                    viewModel.fetchCartList()
                }
                Status.ERROR -> {
                    showToast(
                        response.error?.message,
                        MDToast.TYPE_ERROR
                    )
                }
            }
        }
    }
}