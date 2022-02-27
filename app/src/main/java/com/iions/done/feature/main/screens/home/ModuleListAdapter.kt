package com.iions.done.feature.main.screens.home

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.iions.done.R
import com.iions.done.base.BaseAdapter
import com.iions.done.base.BaseViewHolder
import com.iions.done.databinding.ItemHomeModuleBinding
import com.iions.done.feature.main.data.model.ModuleResponse

class ModuleListAdapter(
    private var dataList: MutableList<ModuleResponse>,
    private val onItemSelectedListener: (ModuleResponse) -> Unit
) : BaseAdapter<ModuleResponse, ModuleListAdapter.ModuleListViewHolder>() {

    override fun getViewHolder(binding: ViewDataBinding, viewType: Int): ModuleListViewHolder {
        return ModuleListViewHolder(binding as ItemHomeModuleBinding)
    }

    override fun onBindCustomViewHolder(holder: ModuleListViewHolder, position: Int) {
        return holder.bindView(dataList[position])
    }

    override fun getLayoutResource(viewType: Int): Int {
        return R.layout.item_home_module
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(list: MutableList<ModuleResponse>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    inner class ModuleListViewHolder(private var binding: ItemHomeModuleBinding) :
        BaseViewHolder<ModuleResponse>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(obj: ModuleResponse) {
            super.bindView(obj)
            binding.tvTitle.text = obj.name
            Glide.with(binding.root.context).load(obj.image)
                .placeholder(R.drawable.logo).into(binding.ivCategories)

            binding.constraint.setOnClickListener {
                onItemSelectedListener(obj)
            }
        }
    }
}