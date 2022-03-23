package com.iions.done.feature.groceries.screen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.NestedScrollView
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityGroceryBinding
import com.iions.done.feature.auth.screens.login.smslogin.SmsLoginActivity
import com.iions.done.feature.groceries.data.model.GroceryResponse
import com.iions.done.feature.groceries.screen.detail.GroceryDetailActivity
import com.iions.done.feature.main.data.model.BannerResponse
import com.iions.done.feature.main.screens.home.HomeSliderAdapter
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.visible
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroceryActivity : BaseActivity<ActivityGroceryBinding>() {
    private val dataList: ArrayList<GroceryResponse> = arrayListOf()
    private val viewModel: GroceryViewModel by viewModels()
    private var page: Int = 1
    private var totalPage: Int = 1

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
                    if (page <= totalPage)
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
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        totalPage = it.items?.lastPage ?: 1
                        it.items?.data?.toList()?.let { it1 -> dataList.addAll(it1) }

                        adapter = GroceryListAdapter(dataList.toMutableList()) { response ->
                            GroceryDetailActivity.start(
                                this@GroceryActivity,
                                response.id,
                                response.name
                            )
                        }
                        binding.rvGrocery.adapter = adapter
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
                        errorMessage = if (response.error?.message.toString()
                                .contains("Authentication")
                        ) {
                            getString(R.string.you_havent_logged_in_yet)
                        } else {
                            response.error?.message.toString()
                        },
                        R.drawable.vc_grocery,
                        actionLabel = if (response.error?.message.toString()
                                .contains("Authentication")
                        ) {
                            getString(R.string.login)
                        } else {
                            getString(R.string.retry)
                        }
                    ) {
                        if (it == getString(R.string.login)) {
                            SmsLoginActivity.start(this, false)
                        } else {
                            viewModel.getGroceries("", "", "", page)
                        }
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