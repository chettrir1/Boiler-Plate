package com.iions.done.feature.main.screens.home

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.iions.done.R
import com.iions.done.base.BaseAdapter
import com.iions.done.base.BaseViewHolder
import com.iions.done.databinding.ItemHomeRestaurantBinding
import com.iions.done.feature.main.data.model.HomeRestaurantRemoteResponse

class HomeRestaurantListAdapter(
    private var dataList: MutableList<HomeRestaurantRemoteResponse>,
    private val onItemSelectedListener: (HomeRestaurantRemoteResponse) -> Unit
) : BaseAdapter<HomeRestaurantRemoteResponse, HomeRestaurantListAdapter.RestaurantListViewHolder>() {

    override fun getViewHolder(
        binding: ViewDataBinding,
        viewType: Int
    ): RestaurantListViewHolder {
        return RestaurantListViewHolder(binding as ItemHomeRestaurantBinding)
    }

    override fun onBindCustomViewHolder(holder: RestaurantListViewHolder, position: Int) {
        return holder.bindView(dataList[position])
    }

    override fun getLayoutResource(viewType: Int): Int {
        return R.layout.item_home_restaurant
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(list: MutableList<HomeRestaurantRemoteResponse>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    inner class RestaurantListViewHolder(private var binding: ItemHomeRestaurantBinding) :
        BaseViewHolder<HomeRestaurantRemoteResponse>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(obj: HomeRestaurantRemoteResponse) {
            super.bindView(obj)
            binding.tvTitle.text = obj.name
            if (!obj.coverImage.isNullOrEmpty()) {
                Glide.with(binding.root.context)
                    .load("https://d-one.iionstech.com/storage/${obj.coverImage}")
                    .placeholder(R.drawable.logo).into(binding.ivResturant)
            }else if (!obj.logo.isNullOrEmpty()){
                Glide.with(binding.root.context)
                    .load("https://d-one.iionstech.com/storage/${obj.logo}")
                    .placeholder(R.drawable.logo).into(binding.ivResturant)
            }
            binding.tvDescription.text = obj.address
            binding.constraint.setOnClickListener {
                onItemSelectedListener(obj)
            }
        }
    }
}