package com.iions.done.feature.groceries.screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.iions.done.R
import com.iions.done.feature.groceries.data.model.GroceryResponse
import com.iions.done.feature.main.data.model.HomeGroceryResponse

class GroceryListAdapter(private val onItemSelectedListener: (GroceryResponse) -> Unit) :
    PagingDataAdapter<GroceryResponse, GroceryListAdapter.GroceryListViewHolder>(GroceryComparator) {

    override fun onBindViewHolder(holder: GroceryListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryListViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_grocery, parent, false)
        return GroceryListViewHolder(inflater)
    }

    inner class GroceryListViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val tvTitle: AppCompatTextView = view.findViewById(R.id.tv_title)
        private val tvPrice: AppCompatTextView = view.findViewById(R.id.tv_price)
        private val ivGrocery: AppCompatImageView = view.findViewById(R.id.iv_grocery)
        private val cvGrocery: MaterialCardView = view.findViewById(R.id.cv_grocery)

        fun bind(item: GroceryResponse) {
            tvTitle.text = item.name
            Glide.with(ivGrocery)
                .load("https://d-one.iionstech.com/storage/" + item.mainImageThumbnail)
                .placeholder(R.drawable.logo).into(ivGrocery)
            tvPrice.text = "Rs. 2500"
            cvGrocery.setOnClickListener {
                onItemSelectedListener(item)
            }
        }
    }

    object GroceryComparator : DiffUtil.ItemCallback<GroceryResponse>() {
        override fun areItemsTheSame(
            oldItem: GroceryResponse,
            newItem: GroceryResponse
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: GroceryResponse,
            newItem: GroceryResponse
        ) = oldItem == newItem
    }
}