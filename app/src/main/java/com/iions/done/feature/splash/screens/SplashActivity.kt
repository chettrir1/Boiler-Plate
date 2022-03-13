package com.iions.done.feature.splash.screens

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.constraintlayout.motion.widget.MotionLayout
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivitySplashBinding
import com.iions.done.feature.main.screens.MainActivity
import com.iions.done.utils.alertdialog.showAlert
import com.iions.done.utils.archcomponents.Status
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(), MotionLayout.TransitionListener {
    private val viewModel: SplashViewModel by viewModels()

    override fun layout() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        binding.motionLayout.setTransitionListener(this)
    }

    override fun initObservers() {
        observeHomeResponse()
    }

    private fun observeHomeResponse() {
        viewModel.homeResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        MainActivity.start(this)
                    }
                }
                Status.ERROR -> {
                    showAlert(
                        title = "Alert!",
                        message = "Looks like your data did not load properly, Please make sure either your internet is working properly or you are connected to internet!",
                        imageRes = null,
                        buttons = arrayOf(getString(R.string.retry), getString(R.string.exit)),
                        handler = { index ->
                            when (index) {
                                0 -> {
                                    binding.motionLayout.setTransitionListener(this)
                                }
                                else -> Unit
                            }
                        }
                    )
                }
            }
        }
    }

    override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {
        viewModel.getHomeResponse()
    }

    override fun onTransitionChange(
        motionLayout: MotionLayout?,
        startId: Int,
        endId: Int,
        progress: Float
    ) {
    }

    override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
    }

    override fun onTransitionTrigger(
        motionLayout: MotionLayout?,
        triggerId: Int,
        positive: Boolean,
        progress: Float
    ) {
    }
}
