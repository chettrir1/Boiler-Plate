package com.iions.done.feature.restaurants.screen.detail

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.iions.done.R
import com.iions.done.base.BaseAdapter
import com.iions.done.base.BaseViewHolder
import com.iions.done.databinding.ItemRestaurantMenuBinding
import com.iions.done.feature.restaurants.data.model.RestaurantMenuResponse

class RestaurantMenuListAdapter(
    private var dataList: MutableList<RestaurantMenuResponse>,
    private val onItemSelectedListener: (RestaurantMenuResponse) -> Unit
) : BaseAdapter<RestaurantMenuResponse, RestaurantMenuListAdapter.MenuListViewHolder>() {

    override fun getViewHolder(binding: ViewDataBinding, viewType: Int): MenuListViewHolder {
        return MenuListViewHolder(binding as ItemRestaurantMenuBinding)
    }

    override fun onBindCustomViewHolder(holder: MenuListViewHolder, position: Int) {
        return holder.bindView(dataList[position])
    }

    override fun getLayoutResource(viewType: Int): Int {
        return R.layout.item_grocery
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(list: MutableList<RestaurantMenuResponse>) {
        this.dataList = list
    }

    inner class MenuListViewHolder(private var binding: ItemRestaurantMenuBinding) :
        BaseViewHolder<RestaurantMenuResponse>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(obj: RestaurantMenuResponse) {
            super.bindView(obj)
            binding.tvTitle.text = obj.name
            if (!obj.image.isNullOrEmpty())
                Glide.with(binding.root.context)
                    .load("https://d-one.iionstech.com/storage/${obj.image}")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.logo).into(binding.ivMenu)

            binding.tvPrice.text = "Rs. ${obj.price}"
            binding.cvMenu.setOnClickListener {
                onItemSelectedListener(obj)
            }
        }
    }
}