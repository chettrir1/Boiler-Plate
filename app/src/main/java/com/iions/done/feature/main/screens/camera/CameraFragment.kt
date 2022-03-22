package com.iions.done.feature.main.screens.camera

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.iions.done.R
import com.iions.done.base.BaseFragment
import com.iions.done.databinding.FragmentCameraBinding
import com.iions.done.utils.enablePianoEffect
import com.iions.done.utils.showToast
import com.valdesekamdem.library.mdtoast.MDToast


class CameraFragment : BaseFragment<FragmentCameraBinding>() {
    override fun layout(): Int = R.layout.fragment_camera

    companion object {
        fun getInstance(): Fragment {
            return CameraFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnContinue.enablePianoEffect().setOnClickListener {
            showToast(
                "Feature not implemented yet!",
                MDToast.TYPE_INFO
            )
        }
    }

    override fun initObservers() {
    }

}