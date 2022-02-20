package com.iions.done.feature.main.screens.camera

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.iions.done.R
import com.iions.done.base.BaseFragment
import com.iions.done.databinding.FragmentCameraBinding
import com.iions.done.feature.main.screens.history.HistoryFragment


class CameraFragment : BaseFragment<FragmentCameraBinding>() {
    override fun layout(): Int = R.layout.fragment_camera

    companion object {
        fun getInstance(): Fragment {
            return CameraFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initObservers() {
    }

}