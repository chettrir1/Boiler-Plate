package com.iions.done.feature.resturants

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityRestaurantBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantActivity : BaseActivity<ActivityRestaurantBinding>() {

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, RestaurantActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.includeToolbar.tvTitle.text = getString(R.string.resturants)

        binding.includeToolbar.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun layout() = R.layout.activity_restaurant

    override fun initObservers() {
    }
}