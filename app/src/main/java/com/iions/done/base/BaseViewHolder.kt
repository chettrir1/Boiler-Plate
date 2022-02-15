package com.iions.done.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder(open val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    open fun bind(item: Any) {
//        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}