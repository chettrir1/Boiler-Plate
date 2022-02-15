package com.iions.done.feature.main.screens.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.iions.done.R
import com.iions.done.base.BaseFragment
import com.iions.done.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun layout(): Int = R.layout.fragment_profile

    companion object {
        fun getInstance(): Fragment {
            return ProfileFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), "Profile Fragment", Toast.LENGTH_SHORT).show()
    }

    override fun initObservers() {
    }

}