package com.iions.done.feature.summary.screens.address

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.iions.done.R
import com.iions.done.base.BaseDialogFragment
import com.iions.done.databinding.FragmentAddressSearchBinding
import com.iions.done.feature.summary.data.model.AddressSearchResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressSearchFragment(
    private val items: ArrayList<AddressSearchResponse>,
    private val onItemSelectedListener: (AddressSearchResponse) -> Unit
) : BaseDialogFragment<FragmentAddressSearchBinding>() {

    private lateinit var adapter: AddressSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun layout(): Int = R.layout.fragment_address_search

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.setOnQueryTextListener(onQueryTextChangeListener)

        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
        setRecyclerView(items)
    }

    private fun setRecyclerView(items: ArrayList<AddressSearchResponse>) {
        adapter = AddressSearchAdapter {}
        adapter.setSearchItems(items)
        binding.rvSearch.adapter = adapter
        binding.searchView
    }

    private val onQueryTextChangeListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            if (!TextUtils.isEmpty(query)) {
                adapter.filter.filter(query)
            }
            return false
        }

        override fun onQueryTextChange(newText: String): Boolean {
            adapter.filter.filter(newText)
            return false
        }
    }

    override fun initObservers() {
    }

}
