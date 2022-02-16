package com.iions.done.feature.main.screens.home

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.iions.done.R
import com.iions.done.base.BaseAdapter
import com.iions.done.base.BaseViewHolder
import com.iions.done.databinding.ItemHomeCategoriesBinding
import com.iions.done.feature.main.data.model.CategoriesResponse

class CategoryListAdapter(
    private var dataList: MutableList<CategoriesResponse>,
    private val onItemSelectedListener: (CategoriesResponse) -> Unit
) : BaseAdapter<CategoriesResponse, CategoryListAdapter.CategoryListViewHolder>() {

    override fun getViewHolder(binding: ViewDataBinding, viewType: Int): CategoryListViewHolder {
        return CategoryListViewHolder(binding as ItemHomeCategoriesBinding)
    }

    override fun onBindCustomViewHolder(holder: CategoryListViewHolder, position: Int) {
        return holder.bindView(dataList[position])
    }

    override fun getLayoutResource(viewType: Int): Int {
        return R.layout.item_home_categories
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(list: MutableList<CategoriesResponse>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    inner class CategoryListViewHolder(private var binding: ItemHomeCategoriesBinding) :
        BaseViewHolder<CategoriesResponse>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(obj: CategoriesResponse) {
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