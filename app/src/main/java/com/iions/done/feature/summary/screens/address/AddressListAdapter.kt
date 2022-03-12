package com.iions.done.feature.summary.screens.address

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.iions.done.R
import com.iions.done.base.BaseAdapter
import com.iions.done.base.BaseViewHolder
import com.iions.done.databinding.ItemAddressBinding
import com.iions.done.feature.auth.data.model.AddressResponse

class AddressListAdapter(
    private var dataList: MutableList<AddressResponse>,
    private val onItemSelectedListener: (AddressResponse) -> Unit
) : BaseAdapter<AddressResponse, AddressListAdapter.AddressListViewHolder>() {
    private var mCheckedPosition = -1

    override fun getViewHolder(binding: ViewDataBinding, viewType: Int): AddressListViewHolder {
        return AddressListViewHolder(binding as ItemAddressBinding)
    }

    override fun onBindCustomViewHolder(holder: AddressListViewHolder, position: Int) {
        return holder.bindView(dataList[position])
    }

    override fun getLayoutResource(viewType: Int): Int {
        return R.layout.item_address
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(list: MutableList<AddressResponse>) {
        this.dataList = list
    }

    inner class AddressListViewHolder(private var binding: ItemAddressBinding) :
        BaseViewHolder<AddressResponse>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(obj: AddressResponse) {
            super.bindView(obj)
            binding.tvDistrict.text = obj.district
            binding.tvArea.text = "${obj.street} , ${obj.localAddress}"
            if (mCheckedPosition == -1) {
                if (absoluteAdapterPosition == 0) {
                    setSelectedItem(binding, true)
                    if (mCheckedPosition != absoluteAdapterPosition) {
                        mCheckedPosition = absoluteAdapterPosition
                    }
                } else
                    setSelectedItem(binding, false)
            } else {
                if (mCheckedPosition == absoluteAdapterPosition) {
                    setSelectedItem(binding, true)
                } else {
                    setSelectedItem(binding, false)
                }
            }

            binding.root.setOnClickListener {
                setSelectedItem(binding, true)
                if (mCheckedPosition != absoluteAdapterPosition) {
                    setSelectedItem(binding, true)
                    notifyItemChanged(mCheckedPosition)
                    mCheckedPosition = absoluteAdapterPosition
                }
                onItemSelectedListener(obj)
            }
        }
    }

    private fun setSelectedItem(binding: ItemAddressBinding, isSelected: Boolean) {
        binding.root.isSelected = isSelected
    }
}