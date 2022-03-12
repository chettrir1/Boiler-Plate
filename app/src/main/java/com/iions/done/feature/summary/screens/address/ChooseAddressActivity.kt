package com.iions.done.feature.summary.screens.address

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityChooseAddressBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseAddressActivity : BaseActivity<ActivityChooseAddressBinding>() {
    private val viewModel: ChooseAddressViewModel by viewModels()

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, ChooseAddressActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.includeToolbar.tvTitle.text = getString(R.string.choose_address)

        binding.includeToolbar.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun layout() = R.layout.activity_choose_address

    override fun initObservers() {
    }

}