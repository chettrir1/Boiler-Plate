package com.iions.done.feature.main.screens.profile.edit

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.iions.Constants
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityEditProfileBinding
import com.iions.done.feature.auth.screens.login.smslogin.SmsLoginViewModel
import com.iions.done.feature.auth.screens.verifypin.VerifyPinActivity
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.progressdialog.ProgressDialog
import com.iions.done.utils.showToast
import com.valdesekamdem.library.mdtoast.MDToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileActivity : BaseActivity<ActivityEditProfileBinding>() {
    private val viewModel: EditProfileViewModel by viewModels()

    override fun layout(): Int = R.layout.activity_edit_profile
    private lateinit var dialog: Dialog

    private val name: String? by lazy {
        intent?.getStringExtra(Constants.TYPE_NAME) ?: ""
    }

    private val email: String? by lazy {
        intent?.getStringExtra(Constants.TYPE_EMAIL) ?: ""
    }

    private val phone: String? by lazy {
        intent?.getStringExtra(Constants.TYPE_PHONE) ?: ""
    }


    companion object {
        fun start(activity: Activity, name: String, email: String, phone: String) {
            val intent = Intent(activity, EditProfileActivity::class.java)
            intent.putExtra(Constants.TYPE_NAME, name)
            intent.putExtra(Constants.TYPE_PHONE, email)
            intent.putExtra(Constants.TYPE_EMAIL, phone)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.etName.setText(name)
        binding.etPhoneNumber.setText(phone)
        binding.etEmail.setText(email)
    }

    override fun initObservers() {
        observeEditProfileResponse()
    }

    private fun observeEditProfileResponse() {
        viewModel.editResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    showProgress()
                }
                Status.COMPLETE -> {
                    hideProgress()
                    showToast(
                        getString(R.string.profile_successfully_updated),
                        MDToast.TYPE_SUCCESS
                    )
                }
                Status.ERROR -> {
                    hideProgress()
                    showToast(
                        response.error?.message,
                        MDToast.TYPE_ERROR
                    )
                }
            }
        }
    }

    private fun showProgress() {
        dialog = ProgressDialog.progressDialog(this)
        dialog.show()
    }

    private fun hideProgress() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }
}