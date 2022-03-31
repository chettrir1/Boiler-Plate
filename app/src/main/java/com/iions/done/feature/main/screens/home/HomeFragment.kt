package com.iions.done.feature.main.screens.home

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.iions.done.R
import com.iions.done.base.BaseFragment
import com.iions.done.databinding.FragmentHomeBinding
import com.iions.done.feature.groceries.screen.GroceryActivity
import com.iions.done.feature.groceries.screen.detail.GroceryDetailActivity
import com.iions.done.feature.main.data.model.BannerResponse
import com.iions.done.feature.restaurants.screen.RestaurantActivity
import com.iions.done.feature.restaurants.screen.detail.RestaurantDetailActivity
import com.iions.done.feature.search.screens.SearchActivity
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.enablePianoEffect
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    override fun layout(): Int = R.layout.fragment_home

    companion object {
        fun getInstance(): Fragment {
            return HomeFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchModuleList()
        viewModel.fetchBannerList()
        viewModel.fetchGroceryCategoryList()
        viewModel.fetchRestaurantList()

        binding.includeRestaurant.tvSeeAll.enablePianoEffect().setOnClickListener {
                RestaurantActivity.start(requireActivity())
        }

        binding.includeGrocery.tvSeeAll.enablePianoEffect().setOnClickListener {
            GroceryActivity.start(requireActivity())
        }

        binding.tvSearch.setOnClickListener {
            SearchActivity.start(requireActivity())
        }
    }

    override fun initObservers() {
        observeCategoryResponse()
        observeBannerResponse()
        observeGroceryCategoryResponse()
        observeGroceryResponse()
        observeRestaurantResponse()
    }

    private fun observeCategoryResponse() {
        viewModel.categoryResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    super.showLoading(binding.loadingLayout, getString(R.string.please_wait))
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        binding.includeCategory.recyclerView.layoutManager = setUpLayoutManager()
                        binding.includeCategory.recyclerView.adapter =
                            ModuleListAdapter(it.toMutableList()) {
                                if (it.name == "Restaurant") {
                                    RestaurantActivity.start(requireActivity())
                                } else if (it.name == "Grocery") {
                                    GroceryActivity.start(requireActivity())
                                }
                            }
                        binding.includeCategory.recyclerView.hasFixedSize()
                        ViewCompat.setNestedScrollingEnabled(
                            binding.includeCategory.recyclerView,
                            false
                        )
                    }
                }
                Status.ERROR -> {
                    super.showErrorWithImage(
                        binding.loadingLayout,
                        response.error?.message.toString(),
                        R.drawable.ic_error_home
                    )
                }
            }
        }
    }

    private fun observeGroceryResponse() {
        viewModel.groceryResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        binding.includeGrocery.rvGrocery.layoutManager = setUpLayoutManager()
                        binding.includeGrocery.rvGrocery.adapter =
                            HomeGroceryListAdapter(it.toMutableList()) { response ->
                                GroceryDetailActivity.start(
                                    requireActivity(),
                                    response.id,
                                    response.name
                                )
                            }
                    }
                    binding.includeGrocery.rvGrocery.hasFixedSize()
                    ViewCompat.setNestedScrollingEnabled(binding.includeGrocery.rvGrocery, false)
                }
                Status.ERROR -> {
                    super.showErrorWithImage(
                        binding.loadingLayout,
                        response.error?.message.toString(),
                        R.drawable.ic_error_home
                    )
                }
            }
        }
    }

    private fun observeGroceryCategoryResponse() {
        viewModel.groceryCategoryResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        binding.includeGrocery.rvCategory.layoutManager = setUpLayoutManager()
                        binding.includeGrocery.rvCategory.adapter =
                            HomeGroceryCategoryListAdapter(it.toMutableList()) {
                                viewModel.fetchGroceryList(it.id!!)
                            }
                        viewModel.fetchGroceryList(it.first().id!!)
                    }
                    binding.includeGrocery.rvCategory.hasFixedSize()
                    ViewCompat.setNestedScrollingEnabled(binding.includeGrocery.rvCategory, false)
                }
                Status.ERROR -> {
                    super.showErrorWithImage(
                        binding.loadingLayout,
                        response.error?.message.toString(),
                        R.drawable.ic_error_home
                    )
                }
            }
        }
    }

    private fun observeBannerResponse() {
        viewModel.bannerResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        setUpBanner(it)
                    }
                }
                Status.ERROR -> {
                }
            }
        }
    }

    private fun observeRestaurantResponse() {
        viewModel.restaurantResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        binding.includeRestaurant.rvRestaurant.layoutManager = setUpLayoutManager()
                        binding.includeRestaurant.rvRestaurant.adapter =
                            HomeRestaurantListAdapter(it.toMutableList()) {
                                RestaurantDetailActivity.start(requireActivity(), it.id!!)
                            }
                    }
                    binding.includeRestaurant.rvRestaurant.hasFixedSize()
                    ViewCompat.setNestedScrollingEnabled(
                        binding.includeRestaurant.rvRestaurant,
                        false
                    )
                    super.showData(binding.loadingLayout)
                }
                Status.ERROR -> {
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

    private fun setUpLayoutManager(): LinearLayoutManager {
        return LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }
}