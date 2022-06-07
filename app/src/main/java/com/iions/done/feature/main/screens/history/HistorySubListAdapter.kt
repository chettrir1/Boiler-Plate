package com.iions.done.feature.main.screens.history

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.iions.done.R
import com.iions.done.base.BaseAdapter
import com.iions.done.base.BaseViewHolder
import com.iions.done.databinding.ItemSubOrderHistoryBinding
import com.iions.done.feature.main.data.model.CartResponse
import com.iions.done.remote.Constants
import com.iions.done.utils.Commons
import com.iions.done.utils.gone
import com.iions.done.utils.visible

class HistorySubListAdapter(
    private var dataList: MutableList<CartResponse>,
    private var orderId: Int?,
    private var status: Int?,
    private val onItemSelectedListener: (CartResponse) -> Unit,
) : BaseAdapter<CartResponse, HistorySubListAdapter.HistorySubListViewHolder>() {

    override fun getViewHolder(binding: ViewDataBinding, viewType: Int): HistorySubListViewHolder {
        return HistorySubListViewHolder(binding as ItemSubOrderHistoryBinding)
    }

    override fun onBindCustomViewHolder(holder: HistorySubListViewHolder, position: Int) {
        return holder.bindView(dataList[position])
    }

    override fun getLayoutResource(viewType: Int): Int {
        return R.layout.item_sub_order_history
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(list: MutableList<CartResponse>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    inner class HistorySubListViewHolder(private var binding: ItemSubOrderHistoryBinding) :
        BaseViewHolder<CartResponse>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(obj: CartResponse) {
            super.bindView(obj)
            binding.tvOrderId.text = "Order ID : #${orderId.toString()}"
            binding.tvStatus.text = "Status : ${getStatus()}"

            if (obj.item?.image!=null) {
                Glide.with(binding.root.context)
                    .load("https://d-one.iionstech.com/storage/${obj.item?.image?:""}")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.logo).into(binding.ivCart)
            }

            if (obj.item?.name!=null)
            binding.tvName.text = obj.item?.name?:""

            if (absoluteAdapterPosition == dataList.size - 1) {
                binding.divider.gone()
            }

            if (absoluteAdapterPosition == 0) {
                binding.tvOrderId.visible()
                binding.tvStatus.visible()
            } else {
                binding.tvOrderId.gone()
                binding.tvStatus.gone()
            }

            val total = obj.quantity?.times(obj.price ?: 0)
            if (total != null) {
                binding.tvPrice.text = "Rs. ${Commons.currencyFormatter(total)}"
            }
        }
    }

    private fun getStatus(): String {
        return when (status) {
            0 -> {
                Constants.ORDER_DRAFT
            }
            1 -> {
                Constants.ORDER_SUCCESS
            }
            2 -> {
                Constants.ORDER_CANCELED
            }
            else -> {
                Constants.ORDER_FAILED
            }
        }
    }
}