package com.iions.done.feature.appointment.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.NestedScrollView
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityAppointmentBinding
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.visible

class AppointmentActivity : BaseActivity<ActivityAppointmentBinding>() {

    private val dataList: ArrayList<AppointmentResponse> = arrayListOf()
    private val viewModel: AppointmentViewModel by viewModels()
    private var page: Int = 1
    private var totalPage: Int = 1

    private var adapter: AppointmentListAdapter? = null

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, AppointmentActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.includeToolbar.tvTitle.text = getString(R.string.appointment)
        binding.includeToolbar.ivBack.setOnClickListener {
            onBackPressed()
        }

        viewModel.getAppointment("", page)

        binding.nestedScrollView.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    page++
                    if (page <= totalPage)
                        viewModel.getAppointment("", page)
                }
            })
    }

    override fun layout() = R.layout.activity_grocery

    override fun initObservers() {
        observeAppointmentResponse()
    }

    private fun observeAppointmentResponse() {
        viewModel.appointmentResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    if (page <= 1)
                        showLoading(binding.loadingLayout, getString(R.string.please_wait))
                }
                Status.COMPLETE -> {
                    response.data?.let {
                        totalPage = it.items?.lastPage ?: 1
                        it.items?.data?.toList()?.let { it1 -> dataList.addAll(it1) }

                        adapter = AppointmentListAdapter(dataList.toMutableList()) { response ->
                        }
                        binding.rvAppointment.adapter = adapter
                    }
                    binding.nestedScrollView.visible()
                    showData(binding.loadingLayout)
                }
                Status.ERROR -> {
                    super.showActionableError(
                        binding.loadingLayout,
                        errorMessage = response.error?.message.toString(),
                        R.drawable.vc_appointment,
                        actionLabel = getString(R.string.retry)

                    ) {
                        viewModel.getAppointment("", page)
                    }

                }
            }
        }
    }
}