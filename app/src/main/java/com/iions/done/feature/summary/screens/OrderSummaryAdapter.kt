package com.iions.done.feature.summary.screens

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.iions.done.R
import com.iions.done.base.BaseAdapter
import com.iions.done.base.BaseViewHolder
import com.iions.done.databinding.ItemOrderSummaryBinding
import com.iions.done.feature.main.data.model.CartResponse
import com.iions.done.utils.Commons

class OrderSummaryAdapter(
    private var dataList: MutableList<CartResponse>
) : BaseAdapter<CartResponse, OrderSummaryAdapter.CartListViewHolder>() {

    override fun getViewHolder(binding: ViewDataBinding, viewType: Int): CartListViewHolder {
        return CartListViewHolder(binding as ItemOrderSummaryBinding)
    }

    override fun onBindCustomViewHolder(holder: CartListViewHolder, position: Int) {
        return holder.bindView(dataList[position])
    }

    override fun getLayoutResource(viewType: Int): Int {
        return R.layout.item_order_summary
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(list: MutableList<CartResponse>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    inner class CartListViewHolder(private var binding: ItemOrderSummaryBinding) :
        BaseViewHolder<CartResponse>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(obj: CartResponse) {
            super.bindView(obj)
            binding.tvName.text = "${obj.item.name} * (${obj.quantity})"
            val total = obj.quantity?.times(obj.price!!)
            if (total != null) {
                binding.tvPrice.text = "Rs. ${Commons.currencyFormatter(total)}"
            }
        }
    }
}