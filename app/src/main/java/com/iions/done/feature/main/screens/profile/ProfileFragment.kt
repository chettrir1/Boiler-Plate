package com.iions.done.feature.main.screens.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.github.dhaval2404.imagepicker.ImagePicker
import com.iions.done.R
import com.iions.done.base.BaseFragment
import com.iions.done.databinding.FragmentProfileBinding
import com.iions.done.feature.auth.screens.login.smslogin.SmsLoginActivity
import com.iions.done.feature.main.data.model.ProfileBaseResponse
import com.iions.done.feature.main.screens.profile.edit.EditProfileActivity
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.gone
import com.iions.done.utils.showToast
import com.iions.done.utils.visible
import com.valdesekamdem.library.mdtoast.MDToast
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

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

        binding.includeProfiles.cvAddImage.setOnClickListener {
            openCamera()
        }
        binding.includeProfiles.cvProfile.setOnClickListener {
            openCamera()
        }
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.isUserLoggedIn()) {
            viewModel.fetchProfileResponse()
        } else {
            super.showActionableError(
                binding.loadingLayout,
                errorMessage = getString(R.string.you_havent_logged_in_yet),
                R.drawable.vc_profile,
                actionLabel = getString(R.string.login)
            ) {
                SmsLoginActivity.start(requireActivity(), false)
            }
        }
    }

    override fun initObservers() {
        observeProfileResponse()
        observeAddressResponse()
        observeEditProfileResponse()
    }

    private fun observeAddressResponse() {
        viewModel.addressResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        if (it.isNotEmpty()) {
                            binding.rvAddress.adapter =
                                ProfileAddressListAdapter(it.toMutableList()) {}
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

    private fun observeProfileResponse() {
        viewModel.profileResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    binding.loadingLayout.thumb.gone()
                    super.showLoading(binding.loadingLayout, getString(R.string.please_wait))
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        setView(it)
                        viewModel.fetchAddressList()
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
                        R.drawable.vc_profile,
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
                            viewModel.fetchProfileResponse()
                        }
                    }
                }
            }
        }
    }

    private fun observeEditProfileResponse() {
        viewModel.editResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                }
                Status.COMPLETE -> {
                    showToast(
                        getString(R.string.profile_successfully_updated),
                        MDToast.TYPE_SUCCESS
                    )
                }
                Status.ERROR -> {
                    Log.v("uploadError", response.error?.message.toString())
                    showToast(
                        response.error?.message,
                        MDToast.TYPE_ERROR
                    )
                }
            }
        }
    }

    private fun setView(it: ProfileBaseResponse) {
        binding.includeProfiles.tvProfileName.text = it.user?.name ?: "Unknown"
        binding.includeProfiles.tvProfileNumber.text = it.user?.phoneNumber ?: "98********"
        binding.includeProfileDetails.tvName.text = it.user?.name ?: "Unknown"
        binding.includeProfileDetails.tvEmail.text =
            it.user?.email ?: "unknown@gmail.com"
        binding.includeProfileDetails.tvPhone.text =
            it.user?.phoneNumber ?: "98********"

        binding.includeProfiles.tvEdit.setOnClickListener { _ ->
            EditProfileActivity.start(
                requireActivity(),
                it.user?.name ?: "Unknown",
                it.user?.email ?: "unknown@gmail.com",
                it.user?.phoneNumber ?: "98********"
            )
        }
    }

    private fun openCamera() {
        ImagePicker.with(this)
            .crop()                    //Crop image(Optional), Check Customization for more option
            .compress(1024)            //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                1080,
                1080
            )    //Final image resolution will be less than 1080 x 1080(Optional)
            .start()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                val path: String = data?.data?.path!!
                Glide.with(binding.root.context)
                    .load(path)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.logo).into(binding.includeProfiles.ivProfile)
                viewModel.editProfileResponse(File(path))
            }
            ImagePicker.RESULT_ERROR -> {
                showToast(
                    ImagePicker.getError(data),
                    MDToast.TYPE_INFO
                )
            }
            else -> {
                showToast(
                    "Task Cancelled",
                    MDToast.TYPE_INFO
                )
            }
        }
    }
}