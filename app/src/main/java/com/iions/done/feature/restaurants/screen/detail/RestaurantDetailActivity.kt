package com.iions.done.feature.restaurants.screen.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.iions.Constants
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityRestaurantDetailBinding
import com.iions.done.feature.restaurants.data.model.RestaurantDetailRemoteBaseResponse
import com.iions.done.utils.archcomponents.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantDetailActivity : BaseActivity<ActivityRestaurantDetailBinding>() {
    private val viewModel: RestaurantDetailViewModel by viewModels()

    override fun layout() = R.layout.activity_restaurant_detail

    companion object {
        fun start(activity: Activity, id: Int) {
            val intent = Intent(activity, RestaurantDetailActivity::class.java)
            intent.putExtra(Constants.GENERIC_ID, id)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getRestaurant(intent.getIntExtra(Constants.GENERIC_ID, 0))
        binding.includeToolbar.tvTitle.text = getString(R.string.restaurants)
        binding.includeToolbar.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun initObservers() {
        observeRestaurantDetailResponse()
    }

    private fun observeRestaurantDetailResponse() {
        viewModel.restaurantResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    showLoading(binding.loadingLayout, getString(R.string.please_wait))
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        setUpView(it)
                    }
                    showData(binding.loadingLayout)
                }
                Status.ERROR -> {
                    super.showActionableError(
                        binding.loadingLayout,
                        errorMessage = response.error?.message.toString(),
                        R.drawable.vc_restaurant,
                        actionLabel = getString(R.string.retry)
                    ) {
                        viewModel.getRestaurant(intent.getIntExtra(Constants.GENERIC_ID, 0))
                    }
                }
            }
        }
    }

    private fun setUpView(response: RestaurantDetailRemoteBaseResponse) {
        response.restaurant.let {
            binding.includeDetail.tvTitle.text = it?.name
            binding.includeDetail.tvAddress.text = it?.address
            val adapter = RestaurantMenuListAdapter(it?.menu!!.toMutableList()) {}
            binding.includeDetail.rvMenu.adapter = adapter
        }
    }
}