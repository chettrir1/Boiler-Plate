package com.iions.done.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.iions.done.BR

abstract class BaseViewHolder<in T>(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    open fun bindView(obj: T) {
        binding.setVariable(BR._all, obj)
        binding.executePendingBindings()
    }
}