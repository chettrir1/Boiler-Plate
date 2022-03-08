package com.iions.done.feature.groceries.screen.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.iions.Constants
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityGroceryDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroceryDetailActivity : BaseActivity<ActivityGroceryDetailBinding>() {

    companion object {
        fun start(activity: Activity, id: String, title: String) {
            val intent = Intent(activity, GroceryDetailActivity::class.java)
            intent.putExtra(Constants.GENERIC_ID, id)
            intent.putExtra(Constants.GENERIC_TITLE, title)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.includeToolbar.tvTitle.text = intent.getStringExtra(Constants.GENERIC_TITLE)
        binding.includeToolbar.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun layout() = R.layout.activity_grocery_detail

    override fun initObservers() {
    }
}