package com.iions.done.feature.groceries

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityGroceryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroceryActivity : BaseActivity<ActivityGroceryBinding>() {

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, GroceryActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.includeToolbar.tvTitle.text = getString(R.string.groceries)

        binding.includeToolbar.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun layout() = R.layout.activity_grocery

    override fun initObservers() {
    }
}