package com.iions.done.feature.summary.screens.address

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityChooseAddressBinding
import com.iions.done.feature.main.data.model.ProfileBaseResponse
import com.iions.done.feature.summary.data.model.AddressSearchResponse
import com.iions.done.feature.summary.screens.confirm.OrderConfirmActivity
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.enablePianoEffect
import com.iions.done.utils.gone
import com.iions.done.utils.progressdialog.ProgressDialog
import com.iions.done.utils.showToast
import com.iions.done.utils.visible
import com.valdesekamdem.library.mdtoast.MDToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseAddressActivity : BaseActivity<ActivityChooseAddressBinding>() {
    private val viewModel: ChooseAddressViewModel by viewModels()
    private var districtId = 0
    private var streetId = 0
    private var addressId = 0
    private var savedLocalAddress = ""
    private lateinit var dialog: Dialog
    private var isCustomAddressClicked = false
    private var isAddressAvailable = false

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, ChooseAddressActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.includeToolbar.tvTitle.text = getString(R.string.choose_address)
        viewModel.fetchProfileResponse()

        binding.includeToolbar.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.tvDistrict.enablePianoEffect().setOnClickListener {
            viewModel.fetchDistrictList()
        }

        binding.tvCity.enablePianoEffect().setOnClickListener {
            if (binding.tvDistrict.text.toString().isNotEmpty()) {
                viewModel.fetchStreetList(districtId)
            } else {
                showToast(
                    getString(R.string.select_district_first_to_get_street),
                    MDToast.TYPE_INFO
                )
            }
        }

        binding.tvTitleCustomAddress.setOnClickListener {
            showHideCustomAddress()
        }

        binding.ivRightArrow.setOnClickListener {
            showHideCustomAddress()
        }

        binding.btnContinue.enablePianoEffect().setOnClickListener {
            val district = binding.tvDistrict.text.toString()
            val street = binding.tvCity.text.toString()
            val localAddress = binding.etLocalArea.text.toString()
            if (!isAddressAvailable) {
                when {
                    district.isEmpty() -> {
                        showToast(
                            getString(R.string.district_field_cannot_be_empty),
                            MDToast.TYPE_ERROR
                        )
                    }
                    street.isEmpty() -> {
                        showToast(
                            getString(R.string.street_field_cannot_be_empty),
                            MDToast.TYPE_ERROR
                        )
                    }
                    localAddress.isEmpty() -> {
                        showToast(
                            getString(R.string.local_area_field_cannot_be_empty),
                            MDToast.TYPE_ERROR
                        )
                    }
                    else -> {
                        viewModel.createOrder("cod", districtId, streetId, localAddress)
                    }
                }
            } else if (districtId > 0 && streetId > 0 && savedLocalAddress.isNotEmpty()) {
                viewModel.createOrder("cod", districtId, streetId, savedLocalAddress)
            } else {
                viewModel.createOrder(cod = "cod", addressId = addressId)
            }
        }

    }

    override fun layout() = R.layout.activity_choose_address

    override fun initObservers() {
        observeProfileResponse()
        observeDistrictResponse()
        observeStreetResponse()
        observeCreateOrderResponse()
    }

    private fun observeProfileResponse() {
        viewModel.profileResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        setView(it)
                    }
                }
                Status.ERROR -> {}
            }
        }
    }

    private fun setView(it: ProfileBaseResponse) {
        if (!it.user?.addresses.isNullOrEmpty()) {
            isAddressAvailable = true
            binding.groupMyAddress.visible()
            binding.groupCustomAddress.gone()
            binding.rvAddress.adapter =
                AddressListAdapter(it.user?.addresses!!.toMutableList()) {
                    addressId = it.district?.id ?: -1
                    streetId = it.street?.id ?: -1
                    savedLocalAddress = it.localAddress ?: ""
                }
            addressId = it.user?.addresses?.first()?.district?.id ?: -1
            streetId = it.user?.addresses?.first()?.street?.id ?: -1
            savedLocalAddress = it.user?.addresses?.first()?.localAddress ?: ""
        } else {
            isAddressAvailable = false
            binding.groupCustomAddress.visible()
            binding.groupMyAddress.gone()
        }
    }

    private fun observeDistrictResponse() {
        viewModel.districtResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        val data = it.map {
                            AddressSearchResponse(id = it.id, name = it.name)
                        }
                        openSearchView(data.toMutableList(), true)
                    }
                }
                Status.ERROR -> {
                }
            }
        }
    }

    private fun observeStreetResponse() {
        viewModel.streetResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        val data = it.map {
                            AddressSearchResponse(id = it.id, name = it.name)
                        }
                        openSearchView(data.toMutableList(), false)
                    }
                }
                Status.ERROR -> {
                }
            }
        }
    }

    private fun observeCreateOrderResponse() {
        viewModel.createOrderResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    showProgress()
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        hideProgress()
                        OrderConfirmActivity.start(this, it)
                    }
                }
                Status.ERROR -> {
                    hideProgress()
                    showToast(
                        response.error?.message.toString(),
                        MDToast.TYPE_ERROR
                    )
                }
            }
        }
    }

    private fun openSearchView(items: MutableList<AddressSearchResponse>, isDistrict: Boolean) {
        val dialog = AddressSearchFragment(items, isDistrict) { item, district ->
            if (district) {
                districtId = item.id ?: -1
                binding.tvDistrict.text = item.name
            } else {
                streetId = item.id ?: -1
                binding.tvCity.text = item.name
            }
        }
        val ft = supportFragmentManager.beginTransaction()
        dialog.show(ft, "search")
    }

    private fun showProgress() {
        dialog = ProgressDialog.progressDialog(this)
        dialog.show()
    }

    private fun hideProgress() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }

    private fun showHideCustomAddress() {
        if (!isCustomAddressClicked) {
            isCustomAddressClicked = true
            binding.groupCustomAddress.visible()
        } else {
            isCustomAddressClicked = false
            binding.groupCustomAddress.gone()
        }
    }

}