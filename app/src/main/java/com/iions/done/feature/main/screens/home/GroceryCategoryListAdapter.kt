package com.iions.done.feature.main.screens.home

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.iions.done.R
import com.iions.done.base.BaseAdapter
import com.iions.done.base.BaseViewHolder
import com.iions.done.databinding.ItemCategoryBinding
import com.iions.done.feature.main.data.model.GroceryCategoryResponse

class GroceryCategoryListAdapter(
    private var dataList: MutableList<GroceryCategoryResponse>,
    private val onItemSelectedListener: (GroceryCategoryResponse) -> Unit
) : BaseAdapter<GroceryCategoryResponse, GroceryCategoryListAdapter.GroceryCategoryListViewHolder>() {

    override fun getViewHolder(
        binding: ViewDataBinding,
        viewType: Int
    ): GroceryCategoryListViewHolder {
        return GroceryCategoryListViewHolder(binding as ItemCategoryBinding)
    }

    override fun onBindCustomViewHolder(holder: GroceryCategoryListViewHolder, position: Int) {
        return holder.bindView(dataList[position])
    }

    override fun getLayoutResource(viewType: Int): Int {
        return R.layout.item_category
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(list: MutableList<GroceryCategoryResponse>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    inner class GroceryCategoryListViewHolder(private var binding: ItemCategoryBinding) :
        BaseViewHolder<GroceryCategoryResponse>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(obj: GroceryCategoryResponse) {
            super.bindView(obj)
            binding.tvName.text = obj.name
            binding.constraint.setOnClickListener {
                onItemSelectedListener(obj)
            }
        }
    }
}