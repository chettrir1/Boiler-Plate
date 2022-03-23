package com.iions.done.feature.main.screens.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iions.done.R
import com.iions.done.base.BaseFragment
import com.iions.done.databinding.FragmentHistoryBinding
import com.iions.done.feature.auth.screens.login.smslogin.SmsLoginActivity
import com.iions.done.feature.groceries.screen.GroceryActivity
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.gone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {
    private val viewModel: HistoryViewModel by viewModels()

    override fun layout(): Int = R.layout.fragment_history

    companion object {
        fun getInstance(): Fragment {
            return HistoryFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.isUserLoggedIn()) {
            viewModel.fetchOrdersList()
        } else {
            super.showActionableError(
                binding.loadingLayout,
                errorMessage = getString(R.string.you_havent_logged_in_yet),
                R.drawable.vc_history,
                actionLabel = getString(R.string.login)
            ) {
                SmsLoginActivity.start(requireActivity(), false)
            }
        }
    }

    override fun initObservers() {
        observeOrdersResponse()
    }

    private fun observeOrdersResponse() {
        viewModel.historyResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    binding.loadingLayout.thumb.gone()
                    super.showLoading(binding.loadingLayout, getString(R.string.please_wait))
                }
                Status.COMPLETE -> {
                    response.data?.let { data ->
                        if (data.orders?.isEmpty() == false) {
                            binding.rvOrderHistory.adapter =
                                data.orders?.toMutableList()
                                    ?.let { response ->
                                        HistoryListAdapter(response) {}
                                    }
                            binding.rvOrderHistory.hasFixedSize()
                            super.showData(binding.loadingLayout)
                        } else {
                            super.showActionableError(
                                binding.loadingLayout,
                                errorMessage = getString(R.string.no_item_in_your_order),
                                R.drawable.vc_history,
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
                        R.drawable.vc_history,
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
                            viewModel.fetchOrdersList()
                        }
                    }
                }
            }
        }
    }
}