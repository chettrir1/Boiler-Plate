package com.iions.done.feature.auth.screens.login

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.iions.Constants.TYPE_LOGOUT
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityLoginBinding
import com.iions.done.feature.main.screens.MainActivity
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.enablePianoEffect
import com.iions.done.utils.showToast
import com.valdesekamdem.library.mdtoast.MDToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    private val viewModel: LoginViewModel by viewModels()

    private lateinit var dialog: Dialog


    companion object {
        fun start(activity: Activity, isLogout: Boolean) {
            val intent = Intent(activity, LoginActivity::class.java)
            intent.putExtra(TYPE_LOGOUT, isLogout)
            activity.startActivity(intent)
        }
    }

    override fun layout() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnContinue.enablePianoEffect().setOnClickListener {
            MainActivity.start(this)
        }
    }

    override fun initObservers() {
        observeLoginResponse()
    }

    private fun observeLoginResponse() {
        viewModel.loginResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
//                    showProgress()
                }
                Status.COMPLETE -> {
//                    hideProgress()
                    showToast(
                        getString(R.string.verification_code_sent),
                        MDToast.TYPE_SUCCESS
                    )
                    MainActivity.start(this)
                }
                Status.ERROR -> {
//                    hideProgress()
                    showToast(
                        response.error?.message,
                        MDToast.TYPE_ERROR
                    )
                }
            }
        }
    }

}