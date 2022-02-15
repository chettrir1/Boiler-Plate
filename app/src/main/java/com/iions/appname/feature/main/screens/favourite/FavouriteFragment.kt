package com.iions.appname.feature.main.screens.favourite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.iions.appname.R
import com.iions.appname.base.BaseFragment
import com.iions.appname.databinding.FragmentFavouriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>() {
    override fun layout(): Int = R.layout.fragment_favourite

    companion object {
        fun getInstance(): Fragment {
            return FavouriteFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), "Favourite Fragment", Toast.LENGTH_SHORT).show()
    }

    override fun initObservers() {
    }

}