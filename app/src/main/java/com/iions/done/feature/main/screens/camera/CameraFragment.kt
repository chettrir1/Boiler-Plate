package com.iions.done.feature.main.screens.camera

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.dhaval2404.imagepicker.ImagePicker
import com.iions.done.R
import com.iions.done.base.BaseFragment
import com.iions.done.databinding.FragmentCameraBinding
import com.iions.done.feature.main.screens.camera.upload.UploadActivity
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
            ImagePicker.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                )    //Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }
    }

    override fun initObservers() {
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                val path: String = data?.data?.path!!
                UploadActivity.start(requireActivity(), path)
            }
            ImagePicker.RESULT_ERROR -> {
                showToast(
                    ImagePicker.getError(data),
                    MDToast.TYPE_INFO
                )
            }
            else -> {
                showToast(
                    "Task Cancelled",
                    MDToast.TYPE_INFO
                )
            }
        }
    }

}