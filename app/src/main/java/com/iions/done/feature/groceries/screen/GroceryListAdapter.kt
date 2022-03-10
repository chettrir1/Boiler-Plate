package com.iions.done.feature.groceries.screen

import android.annotation.SuppressLint
import android.graphics.Paint
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.iions.done.R
import com.iions.done.base.BaseAdapter
import com.iions.done.base.BaseViewHolder
import com.iions.done.databinding.ItemGroceryBinding
import com.iions.done.feature.groceries.data.model.GroceryResponse
import com.iions.done.utils.gone
import com.iions.done.utils.visible

class GroceryListAdapter(
    private var dataList: MutableList<GroceryResponse>,
    private val onItemSelectedListener: (GroceryResponse) -> Unit
) : BaseAdapter<GroceryResponse, GroceryListAdapter.GroceryListViewHolder>() {

    override fun getViewHolder(binding: ViewDataBinding, viewType: Int): GroceryListViewHolder {
        return GroceryListViewHolder(binding as ItemGroceryBinding)
    }

    override fun onBindCustomViewHolder(holder: GroceryListViewHolder, position: Int) {
        return holder.bindView(dataList[position])
    }

    override fun getLayoutResource(viewType: Int): Int {
        return R.layout.item_grocery
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(list: MutableList<GroceryResponse>) {
        this.dataList = list
    }

    inner class GroceryListViewHolder(private var binding: ItemGroceryBinding) :
        BaseViewHolder<GroceryResponse>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(obj: GroceryResponse) {
            super.bindView(obj)
            binding.tvTitle.text = obj.name

            if (!obj.mainImageThumbnail.isNullOrEmpty())
                Glide.with(binding.root.context)
                    .load("https://d-one.iionstech.com/storage/${obj.mainImageThumbnail}")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.logo).into(binding.ivGrocery)

            binding.tvPrice.text = "Rs. ${obj.currentPrice}"

            if (obj.oldPrice != null && obj.hasDiscount == true) {
                binding.tvOldPrice.visible()
                binding.tvDiscountPercentage.visible()
                binding.tvOldPrice.text = "Rs. ${obj.oldPrice}"
                binding.tvOldPrice.paintFlags =
                    binding.tvOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                val originalPrice = obj.oldPrice?.toDouble()
                val discountedPrice = obj.currentPrice?.toDouble()
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