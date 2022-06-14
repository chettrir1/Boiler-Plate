package com.iions.done.feature.search.screens

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.iions.done.R
import com.iions.done.base.BaseAdapter
import com.iions.done.base.BaseViewHolder
import com.iions.done.databinding.ItemCustomSearchBinding
import com.iions.done.feature.groceries.data.model.GroceryResponse

class SearchGroceryListAdapter(
    private var dataList: MutableList<GroceryResponse>,
    private val onItemSelectedListener: (GroceryResponse) -> Unit
) : BaseAdapter<GroceryResponse, SearchGroceryListAdapter.RestaurantListViewHolder>() {

    override fun getViewHolder(binding: ViewDataBinding, viewType: Int): RestaurantListViewHolder {
        return RestaurantListViewHolder(binding as ItemCustomSearchBinding)
    }

    override fun onBindCustomViewHolder(holder: RestaurantListViewHolder, position: Int) {
        return holder.bindView(dataList[position])
    }

    override fun getLayoutResource(viewType: Int): Int {
        return R.layout.item_custom_search
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(list: MutableList<GroceryResponse>) {
        this.dataList = list
    }

    inner class RestaurantListViewHolder(private var binding: ItemCustomSearchBinding) :
        BaseViewHolder<GroceryResponse>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(obj: GroceryResponse) {
            super.bindView(obj)
            binding.tvTitle.text = obj.name
            if (!obj.mainImageThumbnail.isNullOrEmpty())
                Glide.with(binding.root.context)
                    .load("https://d-one.iionstech.com/storage/${obj.mainImageThumbnail}")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.logo).into(binding.ivImage)

            binding.tvDescription.text = obj.description
            binding.tvPrice.text = "Rs. ${obj.currentPrice}"

            binding.root.setOnClickListener {
                onItemSelectedListener(obj)
            }
        }
    }
}