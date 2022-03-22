package com.iions.done.feature.restaurants.screen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.NestedScrollView
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityRestaurantBinding
import com.iions.done.feature.main.data.model.BannerResponse
import com.iions.done.feature.main.screens.home.HomeSliderAdapter
import com.iions.done.feature.restaurants.data.model.RestaurantResponse
import com.iions.done.feature.restaurants.screen.detail.RestaurantDetailActivity
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.visible
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantActivity : BaseActivity<ActivityRestaurantBinding>() {
    private val dataList: ArrayList<RestaurantResponse> = arrayListOf()
    private val viewModel: RestaurantViewModel by viewModels()
    private var page: Int = 1
    private var totalPage: Int = 1

    private var adapter: RestaurantListAdapter? = null

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, RestaurantActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.includeToolbar.tvTitle.text = getString(R.string.restaurants)

        binding.includeToolbar.ivBack.setOnClickListener {
            onBackPressed()
        }

        viewModel.getRestaurants("", page)

        binding.nestedScrollView.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    page++
                    if (page <= totalPage)
                        viewModel.getRestaurants("", page)
                }
            })
    }

    override fun layout() = R.layout.activity_restaurant

    override fun initObservers() {
        observeRestaurantResponse()
    }

    private fun observeRestaurantResponse() {
        viewModel.restaurantResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    if (page <= 1)
                        showLoading(binding.loadingLayout, getString(R.string.please_wait))
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        totalPage = it.items?.lastPage ?: 1
                        it.items?.data?.toList()?.let { it1 -> dataList.addAll(it1) }

                        adapter = RestaurantListAdapter(dataList.toMutableList()) { response ->
                            RestaurantDetailActivity.start(this, response.id)
                        }
                        binding.rvResturant.adapter = adapter
                        val items = it.banner?.map {
                            BannerResponse(
                                it.id,
                                "https://d-one.iionstech.com/storage/${it.url}"
                            )
                        }
                        if (items != null) {
                            setUpBanner(items)
                        }
                    }
                    binding.nestedScrollView.visible()
                    showData(binding.loadingLayout)
                }
                Status.ERROR -> {
                    super.showActionableError(
                        binding.loadingLayout,
                        errorMessage = response.error?.message.toString(),
                        R.drawable.vc_restaurant,
                        actionLabel = getString(R.string.retry)
                    ) {
                        viewModel.getRestaurants("", page)
                    }
                }
            }
        }
    }

    private fun setUpBanner(packs: List<BannerResponse>) {
        val adapter =
            HomeSliderAdapter(packs)
        binding.includeSlider.slider.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
        binding.includeSlider.slider.setSliderAdapter(adapter)
        binding.includeSlider.slider.scrollTimeInSec = 3
        binding.includeSlider.slider.isAutoCycle = true
        binding.includeSlider.slider.startAutoCycle()
    }
}