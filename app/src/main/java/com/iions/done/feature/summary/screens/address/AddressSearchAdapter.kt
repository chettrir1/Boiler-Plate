package com.iions.done.feature.summary.screens.address

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.iions.done.databinding.ItemAddressSearchBinding
import com.iions.done.feature.summary.data.model.AddressSearchResponse

class AddressSearchAdapter(private val onItemSelectedListener: (AddressSearchResponse) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private val items = ArrayList<AddressSearchResponse>()
    private var filter = ArrayList<AddressSearchResponse>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAddressSearchBinding.inflate(layoutInflater, parent, false)
        return RecyclerHolder(binding)
    }

    override fun getItemCount(): Int {
        return filter.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val items = filter[position]
        (holder as RecyclerHolder).bind(items)
        holder.binding.root.setOnClickListener {
            onItemSelectedListener(items)
        }
        holder.binding.executePendingBindings()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            @SuppressLint("DefaultLocale")
            override fun performFiltering(char: CharSequence): FilterResults {
                val characterString = char.toString()
                filter = if (characterString.isEmpty()) {
                    items
                } else {
                    val filteredList = ArrayList<AddressSearchResponse>()
                    for (item in items) {
                        if (item.name?.toLowerCase()?.contains(characterString.toLowerCase())!!) {
                            filteredList.add(item)
                        }
                    }
                    filteredList
                }
                val results = FilterResults()
                results.values = filter
                return results
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                if (filter.size > 0) {
                    filter = results.values as ArrayList<AddressSearchResponse>
                }
                notifyDataSetChanged()
            }
        }
    }

    inner class RecyclerHolder(var binding: ItemAddressSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AddressSearchResponse) {
            binding.tvItems.text = item.name
        }
    }

    fun setSearchItems(item: ArrayList<AddressSearchResponse>) {
        items.addAll(item)
        filter.addAll(items)
        notifyDataSetChanged()
    }
}