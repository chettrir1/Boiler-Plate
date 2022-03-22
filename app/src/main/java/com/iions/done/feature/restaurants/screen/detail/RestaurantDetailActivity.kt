package com.iions.done.feature.restaurants.screen.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.iions.Constants
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityRestaurantDetailBinding
import com.iions.done.feature.auth.screens.login.smslogin.SmsLoginActivity
import com.iions.done.feature.restaurants.data.model.RestaurantDetailRemoteBaseResponse
import com.iions.done.feature.restaurants.data.model.RestaurantMenuResponse
import com.iions.done.feature.summary.screens.PaymentOptionActivity
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.enablePianoEffect
import com.iions.done.utils.gone
import com.iions.done.utils.showToast
import com.iions.done.utils.visible
import com.valdesekamdem.library.mdtoast.MDToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantDetailActivity : BaseActivity<ActivityRestaurantDetailBinding>() {
    private val viewModel: RestaurantDetailViewModel by viewModels()
    private var quantity = 0
    private var isOrderNow = false

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

        binding.constraint.setOnClickListener {
            binding.includeAddToCart.constraint.gone()
        }
    }

    override fun initObservers() {
        observeRestaurantDetailResponse()
        observeAddToCartResponse()
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

    private fun observeAddToCartResponse() {
        viewModel.addToCartResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        if (!isOrderNow) {
                            showToast(
                                getString(R.string.item_added_to_cart),
                                MDToast.TYPE_SUCCESS
                            )
                        } else {
                            PaymentOptionActivity.start(this)
                        }
                    }
                }
                Status.ERROR -> {
                    showToast(
                        response.error?.message,
                        MDToast.TYPE_ERROR
                    )
                }
            }
        }
    }

    override fun onBackPressed() {
        if (binding.includeAddToCart.constraint.visibility == View.VISIBLE) {
            binding.includeAddToCart.constraint.gone()
        } else {
            super.onBackPressed()
        }
    }

    private fun setUpView(response: RestaurantDetailRemoteBaseResponse) {
        response.restaurant.let {
            binding.includeDetail.tvTitle.text = it?.name
            binding.includeDetail.tvAddress.text = it?.address
            if (!response.restaurant?.coverImage.isNullOrEmpty()) {
                Glide.with(binding.root.context)
                    .load("https://d-one.iionstech.com/storage/${response.restaurant?.coverImage}")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate()
                    .placeholder(R.drawable.logo).into(binding.slideView)
            }

            val adapter = RestaurantMenuListAdapter(it?.menu!!.toMutableList()) { response ->
                binding.includeAddToCart.tvTitle.text = it.name
                binding.includeAddToCart.tvPrice.text = "Rs. ${response.price}"
                if (!response.image.isNullOrEmpty())
                    Glide.with(binding.root.context)
                        .load("https://d-one.iionstech.com/storage/${response.image}")
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.logo).into(binding.includeAddToCart.ivMenu)
                binding.includeAddToCart.constraint.visible()

                binding.includeAddToCart.btnAddToCart.enablePianoEffect().setOnClickListener {
                    isOrderNow = false
                    addToCart(response)
                }
                binding.includeAddToCart.btnOrderNow.enablePianoEffect().setOnClickListener {
                    isOrderNow = true
                    addToCart(response)
                }

                binding.includeAddToCart.ivAdd.enablePianoEffect().setOnClickListener {
                    addQuantity()
                }
                binding.includeAddToCart.ivMinus.enablePianoEffect().setOnClickListener {
                    if (quantity > 0)
                        removeQuantity()
                }
            }
            binding.includeDetail.rvMenu.adapter = adapter
        }
    }

    private fun addQuantity() {
        quantity++
        display(quantity)
    }

    private fun removeQuantity() {
        quantity--
        display(quantity)
    }

    private fun display(number: Int) {
        binding.includeAddToCart.tvQuantity.text = "$number"
    }

    private fun addToCart(response: RestaurantMenuResponse) {
        if (viewModel.isUserLoggedIn()) {
            viewModel.requestAddToCart(
                itemId = response.id,
                itemType = "restaurant",
                quantity = quantity
            )
        } else {
            SmsLoginActivity.start(this)
        }
    }
}