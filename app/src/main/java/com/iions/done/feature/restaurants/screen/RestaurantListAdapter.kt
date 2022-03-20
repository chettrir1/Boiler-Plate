package com.iions.done.feature.restaurants.screen

import android.annotation.SuppressLint
import android.graphics.Paint
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.iions.done.R
import com.iions.done.base.BaseAdapter
import com.iions.done.base.BaseViewHolder
import com.iions.done.databinding.ItemRestaurantBinding
import com.iions.done.feature.restaurants.data.model.RestaurantResponse

class RestaurantListAdapter(
    private var dataList: MutableList<RestaurantResponse>,
    private val onItemSelectedListener: (RestaurantResponse) -> Unit
) : BaseAdapter<RestaurantResponse, RestaurantListAdapter.RestaurantListViewHolder>() {

    override fun getViewHolder(binding: ViewDataBinding, viewType: Int): RestaurantListViewHolder {
        return RestaurantListViewHolder(binding as ItemRestaurantBinding)
    }

    override fun onBindCustomViewHolder(holder: RestaurantListViewHolder, position: Int) {
        return holder.bindView(dataList[position])
    }

    override fun getLayoutResource(viewType: Int): Int {
        return R.layout.item_restaurant
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(list: MutableList<RestaurantResponse>) {
        this.dataList = list
    }

    inner class RestaurantListViewHolder(private var binding: ItemRestaurantBinding) :
        BaseViewHolder<RestaurantResponse>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(obj: RestaurantResponse) {
            super.bindView(obj)
            binding.tvTitle.text = obj.name
            if (!obj.logo.isNullOrEmpty())
                Glide.with(binding.root.context)
                    .load("https://d-one.iionstech.com/storage/${obj.logo}")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.logo).into(binding.ivResturant)

            binding.tvDescription.text = obj.address

            binding.cvResturant.setOnClickListener {
                onItemSelectedListener(obj)
            }
        }
    }
}