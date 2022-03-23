package com.iions.done.feature.main.screens.history

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.iions.done.R
import com.iions.done.base.BaseAdapter
import com.iions.done.base.BaseViewHolder
import com.iions.done.databinding.ItemOrderHistoryBinding
import com.iions.done.feature.main.data.model.OrdersResponse

class HistoryListAdapter(
    private var dataList: MutableList<OrdersResponse>,
    private val onItemSelectedListener: (OrdersResponse) -> Unit,
) : BaseAdapter<OrdersResponse, HistoryListAdapter.HistoryListViewHolder>() {

    override fun getViewHolder(binding: ViewDataBinding, viewType: Int): HistoryListViewHolder {
        return HistoryListViewHolder(binding as ItemOrderHistoryBinding)
    }

    override fun onBindCustomViewHolder(holder: HistoryListViewHolder, position: Int) {
        return holder.bindView(dataList[position])
    }

    override fun getLayoutResource(viewType: Int): Int {
        return R.layout.item_order_history
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(list: MutableList<OrdersResponse>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    inner class HistoryListViewHolder(private var binding: ItemOrderHistoryBinding) :
        BaseViewHolder<OrdersResponse>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(obj: OrdersResponse) {
            super.bindView(obj)
            binding.tvDate.text = obj.date ?: ""
            binding.rvOrderHistory.adapter =
                obj.cart?.toMutableList()
                    ?.let { response ->
                        HistorySubListAdapter(response, obj.id, obj.status) {}
                    }
            if (obj.cart?.size!! <= 1) {
                binding.tvOrderCount.text = "${obj.cart?.size} item Ordered"
            } else {
                binding.tvOrderCount.text = "${obj.cart?.size} items Ordered"
            }
            binding.rvOrderHistory.hasFixedSize()
        }
    }
}