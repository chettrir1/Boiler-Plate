package com.iions.done.feature.main.screens.cart

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.iions.done.R
import com.iions.done.base.BaseAdapter
import com.iions.done.base.BaseViewHolder
import com.iions.done.databinding.ItemCartBinding
import com.iions.done.feature.main.data.model.CartResponse
import com.iions.done.utils.Commons
import com.iions.done.utils.enablePianoEffect

class CartListAdapter(
    private var dataList: MutableList<CartResponse>,
    private val onItemSelectedListener: (CartResponse, Boolean) -> Unit,
) : BaseAdapter<CartResponse, CartListAdapter.CartListViewHolder>() {

    override fun getViewHolder(binding: ViewDataBinding, viewType: Int): CartListViewHolder {
        return CartListViewHolder(binding as ItemCartBinding)
    }

    override fun onBindCustomViewHolder(holder: CartListViewHolder, position: Int) {
        return holder.bindView(dataList[position])
    }

    override fun getLayoutResource(viewType: Int): Int {
        return R.layout.item_cart
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(list: MutableList<CartResponse>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    inner class CartListViewHolder(private var binding: ItemCartBinding) :
        BaseViewHolder<CartResponse>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(obj: CartResponse) {
            super.bindView(obj)
            binding.tvTitle.text = obj.item?.name
            binding.tvQuantity.text = obj.quantity.toString()
            if (!obj.item?.image.isNullOrEmpty()) {
                Glide.with(binding.root.context)
                    .load("https://d-one.iionstech.com/storage/${obj.item?.image}")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.logo).into(binding.ivCart)
            }

            val total = obj.quantity?.times(obj.price!!)
            if (total != null) {
                binding.tvPrice.text = "Rs. ${Commons.currencyFormatter(total)}"
            }

            binding.constraint.setOnClickListener {
                onItemSelectedListener(obj, false)
            }

            binding.tvDelete.enablePianoEffect().setOnClickListener {
                onItemSelectedListener(obj, true)
            }
        }
    }
}