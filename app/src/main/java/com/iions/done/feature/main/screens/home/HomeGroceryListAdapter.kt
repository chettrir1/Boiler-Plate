package com.iions.done.feature.main.screens.home

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.iions.done.R
import com.iions.done.base.BaseAdapter
import com.iions.done.base.BaseViewHolder
import com.iions.done.databinding.ItemGroceryBinding
import com.iions.done.databinding.ItemHomeGroceryBinding
import com.iions.done.feature.main.data.model.HomeGroceryResponse

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
            Glide.with(binding.root.context).load(obj.image)
                .placeholder(R.drawable.grey_logo).into(binding.ivGrocery)
            binding.tvPrice.text = "Rs. 2500"
            binding.cvGrocery.setOnClickListener {
                onItemSelectedListener(obj)
            }
        }
    }
}