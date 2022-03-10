package com.iions.done.feature.groceries.screen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.NestedScrollView
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityGroceryBinding
import com.iions.done.exceptions.parseError
import com.iions.done.feature.groceries.data.model.GroceryResponse
import com.iions.done.feature.groceries.screen.detail.GroceryDetailActivity
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.gone
import com.iions.done.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroceryActivity : BaseActivity<ActivityGroceryBinding>() {
    private val dataList: ArrayList<GroceryResponse> = arrayListOf()
    private val viewModel: GroceryViewModel by viewModels()
    private var page: Int = 1

    private var adapter: GroceryListAdapter? = null

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
        viewModel.getGroceries("", "", "", page)

        binding.nestedScrollView.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    page++
                    viewModel.getGroceries("", "", "", page)
                }
            })
    }

    override fun layout() = R.layout.activity_grocery

    override fun initObservers() {
        observeGroceryResponse()
    }

    private fun observeGroceryResponse() {
        viewModel.groceryResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    if (page <= 1)
                        showLoading(binding.loadingLayout, getString(R.string.please_wait))
                    else
                        binding.progressBar.visible()
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        it.items?.data?.toList()?.let { it1 -> dataList.addAll(it1) }

                        adapter = GroceryListAdapter(dataList.toMutableList()) { response ->
                            GroceryDetailActivity.start(
                                this@GroceryActivity,
                                response.id,
                                response.name
                            ) }
                        binding.rvGrocery.adapter = adapter
                    }
                    binding.progressBar.gone()
                    binding.nestedScrollView.visible()
                    showData(binding.loadingLayout)

                }
                Status.ERROR -> {
                    super.showActionableError(
                        binding.loadingLayout,
                        errorMessage = this.parseError(response.error),
                        R.drawable.ic_error_cart,
                        actionLabel = getString(R.string.retry)
                    ) {
                        viewModel.getGroceries("", "", "", page)
                    }
                }
            }
        }
    }
}