package com.iions.done.feature.main.screens.profile

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.iions.done.R
import com.iions.done.base.BaseAdapter
import com.iions.done.base.BaseViewHolder
import com.iions.done.databinding.ItemProfileShippingAddressBinding
import com.iions.done.feature.auth.data.model.AddressResponse

class ProfileAddressListAdapter(
    private var dataList: MutableList<AddressResponse>,
    private val onItemSelectedListener: (AddressResponse) -> Unit
) : BaseAdapter<AddressResponse, ProfileAddressListAdapter.AddressListViewHolder>() {
    override fun getViewHolder(binding: ViewDataBinding, viewType: Int): AddressListViewHolder {
        return AddressListViewHolder(binding as ItemProfileShippingAddressBinding)
    }

    override fun onBindCustomViewHolder(holder: AddressListViewHolder, position: Int) {
        return holder.bindView(dataList[position])
    }

    override fun getLayoutResource(viewType: Int): Int {
        return R.layout.item_profile_shipping_address
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(list: MutableList<AddressResponse>) {
        this.dataList = list
    }

    inner class AddressListViewHolder(private var binding: ItemProfileShippingAddressBinding) :
        BaseViewHolder<AddressResponse>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(obj: AddressResponse) {
            super.bindView(obj)
            binding.tvDistrict.text = obj.district
            binding.tvStreet.text = obj.street
            binding.tvLocalAddress.text = obj.localAddress
            binding.ivDelete.setOnClickListener {
                onItemSelectedListener(obj)
            }
        }
    }
}