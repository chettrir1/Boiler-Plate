package com.iions.done.feature.main.screens.home

import android.annotation.SuppressLint
import android.graphics.Paint
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.iions.done.R
import com.iions.done.base.BaseAdapter
import com.iions.done.base.BaseViewHolder
import com.iions.done.databinding.ItemGroceryBinding
import com.iions.done.databinding.ItemHomeGroceryBinding
import com.iions.done.feature.main.data.model.HomeGroceryResponse
import com.iions.done.utils.gone
import com.iions.done.utils.visible

class HomeGroceryListAdapter(
    private var dataList: MutableList<HomeGroceryResponse>,
    private val onItemSelectedListener: (HomeGroceryResponse) -> Unit
) : BaseAdapter<HomeGroceryResponse, HomeGroceryListAdapter.GroceryListViewHolder>() {

    override fun getViewHolder(
        binding: ViewDataBinding,
        viewType: Int
    ): GroceryListViewHolder {
        return GroceryListViewHolder(binding as ItemHomeGroceryBinding)
    }

    override fun onBindCustomViewHolder(holder: GroceryListViewHolder, position: Int) {
        return holder.bindView(dataList[position])
    }

    override fun getLayoutResource(viewType: Int): Int {
        return R.layout.item_home_grocery
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(list: MutableList<HomeGroceryResponse>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    inner class GroceryListViewHolder(private var binding: ItemHomeGroceryBinding) :
        BaseViewHolder<HomeGroceryResponse>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(obj: HomeGroceryResponse) {
            super.bindView(obj)
            binding.tvTitle.text = obj.name
            Glide.with(binding.root.context).load("https://d-one.iionstech.com/storage/${obj.image}")
                .placeholder(R.drawable.logo).into(binding.ivGrocery)
            binding.tvPrice.text = "Rs. ${obj.price}"

            if (obj.oldPrice != null && obj.hasDiscount ?: 0 > 0) {
                binding.tvOldPrice.visible()
                binding.tvDiscountPercentage.gone()
                binding.tvOldPrice.text = "Rs. ${obj.oldPrice}"
                binding.tvOldPrice.paintFlags =
                    binding.tvOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                val originalPrice = obj.oldPrice?.toDouble()
                val discountedPrice = obj.price?.toDouble()
                val discountAmount = originalPrice!! - discountedPrice!!
                val value = discountAmount / originalPrice
                val discountPercentage = value * 100
                binding.tvDiscountPercentage.text = "($discountPercentage) % off"
            } else {
                binding.tvOldPrice.gone()
                binding.tvDiscountPercentage.gone()
            }
            binding.cvGrocery.setOnClickListener {
                onItemSelectedListener(obj)
            }
        }
    }
}