package com.iions.done.feature.main.screens.cart

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iions.done.R
import com.iions.done.base.BaseFragment
import com.iions.done.databinding.FragmentCartBinding
import com.iions.done.utils.archcomponents.Status
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
        viewModel.fetchCartList()
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
                    response.data?.let {
                        binding.rvCart.adapter =
                            it.cart?.toMutableList()
                                ?.let { response -> CartListAdapter(response) {} }
                    }
                    binding.rvCart.hasFixedSize()
                    super.showData(binding.loadingLayout)
                }
                Status.ERROR -> {
                    super.showError(binding.loadingLayout, response.error.toString())
                }
            }
        }
    }
}