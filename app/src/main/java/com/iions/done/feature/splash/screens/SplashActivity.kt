package com.iions.done.feature.splash.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.constraintlayout.motion.widget.MotionLayout
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivitySplashBinding
import com.iions.done.feature.main.screens.MainActivity
import com.iions.done.utils.archcomponents.Status
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(), MotionLayout.TransitionListener {
    private val viewModel: SplashViewModel by viewModels()

    override fun layout() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                    }
                }
                Status.ERROR -> {
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
    ) {}

    override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
        MainActivity.start(this)
    }

    override fun onTransitionTrigger(
        motionLayout: MotionLayout?,
        triggerId: Int,
        positive: Boolean,
        progress: Float
    ) {}
}
