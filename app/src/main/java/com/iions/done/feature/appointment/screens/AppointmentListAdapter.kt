package com.iions.done.feature.appointment.screens

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.iions.done.R
import com.iions.done.base.BaseAdapter
import com.iions.done.base.BaseViewHolder
import com.iions.done.databinding.ItemAppointmentBinding

class AppointmentListAdapter(
    private var dataList: MutableList<AppointmentResponse>,
    private val onItemSelectedListener: (AppointmentResponse) -> Unit
) : BaseAdapter<AppointmentResponse, AppointmentListAdapter.AppointmentListViewHolder>() {

    override fun getViewHolder(binding: ViewDataBinding, viewType: Int): AppointmentListViewHolder {
        return AppointmentListViewHolder(binding as ItemAppointmentBinding)
    }

    override fun onBindCustomViewHolder(holder: AppointmentListViewHolder, position: Int) {
        return holder.bindView(dataList[position])
    }

    override fun getLayoutResource(viewType: Int): Int {
        return R.layout.item_appointment
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(list: MutableList<AppointmentResponse>) {
        this.dataList = list
    }

    inner class AppointmentListViewHolder(private var binding: ItemAppointmentBinding) :
        BaseViewHolder<AppointmentResponse>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(obj: AppointmentResponse) {
            super.bindView(obj)
            binding.tvName.text = obj.title
            binding.tvPhone.text = obj.contact
            binding.tvDescription.text = obj.description
            binding.root.setOnClickListener {
                onItemSelectedListener(obj)
            }
        }
    }
}