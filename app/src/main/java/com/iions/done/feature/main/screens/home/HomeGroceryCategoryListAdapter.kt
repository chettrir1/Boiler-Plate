package com.iions.done.feature.main.screens.home

import android.annotation.SuppressLint
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.iions.done.R
import com.iions.done.base.BaseAdapter
import com.iions.done.base.BaseViewHolder
import com.iions.done.databinding.ItemCategoryBinding
import com.iions.done.feature.main.data.model.HomeGroceryCategoryResponse

class HomeGroceryCategoryListAdapter(
    private var dataList: MutableList<HomeGroceryCategoryResponse>,
    private val onItemSelectedListener: (HomeGroceryCategoryResponse) -> Unit
) : BaseAdapter<HomeGroceryCategoryResponse, HomeGroceryCategoryListAdapter.GroceryCategoryListViewHolder>() {

    private var mCheckedPosition = -1

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

    fun updateData(list: MutableList<HomeGroceryCategoryResponse>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    inner class GroceryCategoryListViewHolder(private var binding: ItemCategoryBinding) :
        BaseViewHolder<HomeGroceryCategoryResponse>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(obj: HomeGroceryCategoryResponse) {
            super.bindView(obj)
            binding.tvName.text = obj.name
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

            binding.constraint.setOnClickListener {
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

    private fun setSelectedItem(binding: ItemCategoryBinding, isSelected: Boolean) {
        if (isSelected) {
            binding.constraint.background =
                ContextCompat.getDrawable(
                    binding.root.context,
                    R.drawable.bg_category_selected
                )
            binding.tvName.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.white_FFFFFF
                )
            )
        } else {
            binding.constraint.background =
                ContextCompat.getDrawable(
                    binding.root.context,
                    R.drawable.bg_category
                )
            binding.tvName.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.black_000000
                )
            )
        }
    }
}