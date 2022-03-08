package com.iions.done.feature.groceries.screen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityGroceryBinding
import com.iions.done.feature.groceries.screen.detail.GroceryDetailActivity
import com.iions.done.utils.archcomponents.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class GroceryActivity : BaseActivity<ActivityGroceryBinding>() {
    private val viewModel: GroceryViewModel by viewModels()

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, GroceryActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.includeToolbar.tvTitle.text = getString(R.string.groceries)
        viewModel.getGroceries("", "", "")
        binding.includeToolbar.ivBack.setOnClickListener {
            onBackPressed()
        }

    }

    override fun layout() = R.layout.activity_grocery

    override fun initObservers() {
        observeGroceryResponse()
    }

    private fun observeGroceryResponse() {
        viewModel.groceryResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    super.showLoading(binding.loadingLayout, getString(R.string.please_wait))
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        lifecycleScope.launchWhenCreated {
                            it.collectLatest {
                                val listAdapter = GroceryListAdapter { response ->
                                    GroceryDetailActivity.start(
                                        this@GroceryActivity,
                                        response.id,
                                        response.name
                                    )
                                }
                                binding.rvGrocery.adapter = listAdapter
                                listAdapter.submitData(it)
                            }
                        }
                    }
                    super.showData(binding.loadingLayout)
                }
                Status.ERROR -> {
                    super.showError(binding.loadingLayout, response.error.toString())
                }
            }
        }
    }
}