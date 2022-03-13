package com.iions.done.feature.main.screens.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iions.done.R
import com.iions.done.base.BaseFragment
import com.iions.done.databinding.FragmentProfileBinding
import com.iions.done.feature.auth.screens.login.smslogin.SmsLoginActivity
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.gone
import com.iions.done.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    private val viewModel: ProfileViewModel by viewModels()

    override fun layout(): Int = R.layout.fragment_profile

    companion object {
        fun getInstance(): Fragment {
            return ProfileFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.isUserLoggedIn()) {
            viewModel.fetchAddressList()
        } else {
            super.showActionableError(
                binding.loadingLayout,
                errorMessage = getString(R.string.you_havent_logged_in_yet),
                R.drawable.vc_profile,
                actionLabel = getString(R.string.login)
            ) {
                SmsLoginActivity.start(requireActivity())
            }
        }
    }

    override fun initObservers() {
        observeAddressResponse()
    }


    private fun observeAddressResponse() {
        viewModel.addressResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    binding.loadingLayout.thumb.gone()
                    super.showLoading(binding.loadingLayout, getString(R.string.please_wait))
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        if (it.isNotEmpty()) {
                            binding.rvAddress.adapter =
                                ProfileAddressListAdapter(it.toMutableList()) {
                                }
                            binding.group.visible()
                        } else {
                            binding.group.gone()
                        }
                    }
                    super.showData(binding.loadingLayout)
                }
                Status.ERROR -> {
                    super.showActionableError(
                        binding.loadingLayout,
                        errorMessage = response.error?.message.toString(),
                        R.drawable.vc_profile,
                        actionLabel = getString(R.string.retry)
                    ) {
                        viewModel.fetchAddressList()
                    }
                }
            }
        }
    }
}