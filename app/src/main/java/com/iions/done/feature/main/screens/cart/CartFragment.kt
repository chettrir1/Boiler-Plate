package com.iions.done.feature.main.screens.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iions.done.R
import com.iions.done.base.BaseFragment
import com.iions.done.databinding.FragmentCartBinding
import com.iions.done.exceptions.parseError
import com.iions.done.feature.auth.screens.login.smslogin.SmsLoginActivity
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.gone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>() {
    private val viewModel: CartViewModel by viewModels()

    override fun layout(): Int = R.layout.fragment_cart

    companion object {
        fun getInstance(): Fragment {
            return CartFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchCartList()
    }

    override fun initObservers() {
        observeCartResponse()
    }

    private fun observeCartResponse() {
        viewModel.cartResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    binding.loadingLayout.thumb.gone()
                    super.showLoading(binding.loadingLayout, getString(R.string.please_wait))
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        binding.rvCart.adapter =
                            it.cart?.toMutableList()
                                ?.let { response -> CartListAdapter(response) {} }
                    }
                    binding.rvCart.hasFixedSize()
                    super.showData(binding.loadingLayout)
                }
                Status.ERROR -> {
                    super.showActionableError(
                        binding.loadingLayout,
                        errorMessage = if (viewModel.isUserLoggedIn()) {
                            this.parseError(response.error)
                        } else {
                            getString(R.string.you_havent_logged_in_yet)
                        },
                        R.drawable.ic_error_cart,
                        actionLabel = if (viewModel.isUserLoggedIn()) {
                            getString(R.string.retry)
                        } else {
                            getString(R.string.login)
                        }
                    ) {
                        if (it == getString(R.string.retry))
                            viewModel.fetchCartList()
                        else
                            SmsLoginActivity.start(requireActivity(), "")
                    }
                }
            }
        }
    }
}