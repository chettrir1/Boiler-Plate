package com.iions.done.feature.main.screens.camera.upload

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.github.dhaval2404.imagepicker.ImagePicker
import com.iions.Constants
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityUploadBinding
import com.iions.done.utils.enablePianoEffect
import com.iions.done.utils.showToast
import com.valdesekamdem.library.mdtoast.MDToast

class UploadActivity : BaseActivity<ActivityUploadBinding>() {
    private val viewModel: UploadViewModel by viewModels()

    private val path: String? by lazy {
        intent?.getStringExtra(Constants.GENERIC_IMAGE) ?: ""
    }

    override fun layout() = R.layout.activity_upload

    companion object {
        fun start(activity: Activity, path: String) {
            val intent = Intent(activity, UploadActivity::class.java)
            intent.putExtra(Constants.GENERIC_IMAGE, path)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.includeToolbar.tvTitle.text = getString(R.string.upload)
        Glide.with(binding.root.context)
            .load(path)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.logo).into(binding.ivUpload)

        binding.includeToolbar.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.tvRetake.enablePianoEffect().setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(620)
                .maxResultSize(
                    620,
                    620
                ).start()
        }
    }

    override fun initObservers() {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                val path: String = data?.data?.path!!
                Glide.with(binding.root.context)
                    .load(path)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.logo).into(binding.ivUpload)
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