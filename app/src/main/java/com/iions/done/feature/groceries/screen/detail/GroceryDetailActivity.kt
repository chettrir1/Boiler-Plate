package com.iions.done.feature.groceries.screen.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.activity.viewModels
import androidx.core.text.HtmlCompat
import com.denzcoskun.imageslider.models.SlideModel
import com.iions.Constants
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityGroceryDetailBinding
import com.iions.done.exceptions.parseError
import com.iions.done.feature.auth.screens.login.smslogin.SmsLoginActivity
import com.iions.done.feature.groceries.data.model.GroceryDetailRemoteBaseResponse
import com.iions.done.utils.ImageGetter
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.enablePianoEffect
import com.iions.done.utils.showToast
import com.valdesekamdem.library.mdtoast.MDToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
class GroceryDetailActivity : BaseActivity<ActivityGroceryDetailBinding>() {
    private val viewModel: GroceryDetailViewModel by viewModels()
    private var quantity = 0

    companion object {
        fun start(activity: Activity, id: Int, title: String) {
            val intent = Intent(activity, GroceryDetailActivity::class.java)
            intent.putExtra(Constants.GENERIC_ID, id)
            intent.putExtra(Constants.GENERIC_TITLE, title)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.includeToolbar.tvTitle.text = getString(R.string.groceries)
        viewModel.getGroceryDetail(intent.getIntExtra(Constants.GENERIC_ID, 0))
        quantity = binding.includeAddToCart.tvQuantity.text.toString().toInt()

        binding.includeToolbar.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.includeAddToCart.ivAdd.enablePianoEffect().setOnClickListener {
            addQuantity()
        }
        binding.includeAddToCart.ivMinus.enablePianoEffect().setOnClickListener {
            if (quantity > 0)
                removeQuantity()
        }
    }

    override fun layout() = R.layout.activity_grocery_detail

    override fun initObservers() {
        observeGroceryDetailResponse()
        observeAddToCartResponse()
    }

    private fun observeGroceryDetailResponse() {
        viewModel.groceryResponse.observe(this) { response ->
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
                    showError(binding.loadingLayout, this.parseError(response.error))
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
                        showToast(
                            getString(R.string.item_added_to_cart),
                            MDToast.TYPE_SUCCESS
                        )
                    }
                }
                Status.ERROR -> {
                    showToast(
                        this.parseError(response.error),
                        MDToast.TYPE_ERROR
                    )
                }
            }
        }
    }

    private fun setUpView(response: GroceryDetailRemoteBaseResponse) {
        quantity = binding.includeAddToCart.tvQuantity.text.toString().toInt()
        binding.includePriceView.tvTitle.text = "${response.item?.name} (${response.item?.unitSize})"
        response.item?.description?.let { displayHtml(it) }
        response.item?.images?.map {
            SlideModel("https://d-one.iionstech.com/storage/${it.original}")
        }?.let { binding.slideView.setImageList(it) }

        binding.includeAddToCart.btnAddToCart.enablePianoEffect().setOnClickListener {
            if (viewModel.isUserLoggedIn()) {
                viewModel.requestAddToCart(
                    itemId = response.item?.id,
                    itemType = "grocery",
                    quantity = quantity
                )
            } else {
                SmsLoginActivity.start(this, "")
            }
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

    private fun displayHtml(html: String) {
        // Creating object of ImageGetter class you just created
        val imageGetter = ImageGetter(resources, binding.includePriceView.tvProductDescription)
        // Using Html framework to parse html
        val styledText = HtmlCompat.fromHtml(
            html,
            HtmlCompat.FROM_HTML_MODE_LEGACY,
            imageGetter, null
        )

        // to enable image/link clicking
        binding.includePriceView.tvProductDescription.movementMethod = LinkMovementMethod.getInstance()

        // setting the text after formatting html and downloading and setting images
        binding.includePriceView.tvProductDescription.text = styledText
    }
}
