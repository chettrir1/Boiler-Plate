package com.iions.done.feature.search.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivitySearchBinding
import com.iions.done.feature.groceries.screen.detail.GroceryDetailActivity
import com.iions.done.feature.restaurants.screen.detail.RestaurantDetailActivity
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.visible
import com.paulrybitskyi.persistentsearchview.utils.VoiceRecognitionDelegate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    private val viewModel: SearchViewModel by viewModels()

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, SearchActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.requestSearch()
        with(binding.searchView) {
            setOnLeftBtnClickListener {
                onBackPressed()
            }
            setOnClearInputBtnClickListener {
                viewModel.requestSearch()
            }

            // Setting a delegate for the voice recognition input
            setVoiceRecognitionDelegate(VoiceRecognitionDelegate(this@SearchActivity))

            setOnSearchConfirmedListener { searchView, query ->
                Log.v("getSearchQuery", query)
                viewModel.requestSearch(query)
                // Handle a search confirmation. This is the place where you'd
                // want to perform a search against your data provider.
            }

            // Disabling the suggestions since they are unused in
            // the simple implementation
            setSuggestionsDisabled(true)
        }
    }

    override fun layout() = R.layout.activity_search

    override fun initObservers() {
        observeSearchResponse()
    }

//    override fun onResume() {
//        super.onResume()
//
//        // Fetching the search queries from the data provider
//        val searchQueries = if (binding.searchView.isInputQueryEmpty) {
//            mDataProvider.getInitialSearchQueries()
//        } else {
//            mDataProvider.getSuggestionsForQuery(binding.searchView.inputQuery)
//        }
//
//        // Converting them to recent suggestions and setting them to the widget
//        binding.searchView.setSuggestions(
//            SuggestionCreationUtil.asRecentSearchSuggestions(
//                searchQueries
//            ), false
//        )
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        VoiceRecognitionDelegate.handleResult(binding.searchView, requestCode, resultCode, data)
    }

    private fun observeSearchResponse() {
        viewModel.searchResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    binding.searchView.collapse()
                    showLoading(binding.loadingLayout, getString(R.string.please_wait))
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        if (it.groceries.isNullOrEmpty() && it.restaurants.isNullOrEmpty()) {
                            super.showError(
                                binding.loadingLayout, getString(R.string.no_data_available_to_load)
                            )
                        } else {
                            binding.rvGrocery.visible(!it.groceries.isNullOrEmpty())
                            binding.rvRestaurant.visible(!it.restaurants.isNullOrEmpty())
                            binding.tvGrocery.visible(!it.groceries.isNullOrEmpty())
                            binding.tvRestaurant.visible(!it.restaurants.isNullOrEmpty())
                            if (!it.groceries.isNullOrEmpty()) {
                                val groceryAdapter =
                                    SearchGroceryListAdapter(it.groceries.toMutableList()) { response ->
                                        GroceryDetailActivity.start(
                                            this,
                                            response.id,
                                            response.name
                                        )
                                    }
                                binding.rvGrocery.adapter = groceryAdapter
                            }
                            if (!it.groceries.isNullOrEmpty()) {
                                val restaurantAdapter =
                                    SearchRestaurantListAdapter(it.restaurants!!.toMutableList()) { response ->
                                        RestaurantDetailActivity.start(this, response.id)
                                    }
                                binding.rvRestaurant.adapter = restaurantAdapter
                            }
                            showData(binding.loadingLayout)
                        }
                    }
                }
                Status.ERROR -> {
                    super.showError(
                        binding.loadingLayout,
                        response.error?.message.toString()
                    )
                }
            }
        }
    }
}