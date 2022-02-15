package com.iions.done.feature.main.screens.cart

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.iions.done.R
import com.iions.done.base.BaseFragment
import com.iions.done.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>() {
    override fun layout(): Int = R.layout.fragment_cart

    companion object {
        fun getInstance(): Fragment {
            return CartFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), "Cart Fragment", Toast.LENGTH_SHORT).show()
    }

    override fun initObservers() {
    }

}