package com.iions.done.feature.main.screens.camera.upload

import android.app.Activity
import android.app.Dialog
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
import com.iions.done.feature.auth.screens.login.smslogin.SmsLoginActivity
import com.iions.done.utils.archcomponents.Status
import com.iions.done.utils.enablePianoEffect
import com.iions.done.utils.progressdialog.ProgressDialog
import com.iions.done.utils.showToast
import com.valdesekamdem.library.mdtoast.MDToast
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class UploadActivity : BaseActivity<ActivityUploadBinding>() {
    private val viewModel: UploadViewModel by viewModels()
    private lateinit var dialog: Dialog
    private var file: File? = null

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
        file = path?.let { File(it) }
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
        binding.btnUpload.setOnClickListener {
            if (viewModel.isUserLoggedIn()) {
                if (file != null) {
                    viewModel.requestLogout(file!!)
                } else {
                    showToast(
                        getString(R.string.select_image_first_to_proceed),
                        MDToast.TYPE_INFO
                    )
                }
            } else {
                SmsLoginActivity.start(this, false)
            }
        }
    }

    override fun initObservers() {
        observeOrderResponse()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                val path: String = data?.data?.path!!
                file = File(path)

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

    private fun observeOrderResponse() {
        viewModel.orderResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    showProgress()
                }
                Status.COMPLETE -> {
                    hideProgress()
                    showToast(
                        getString(R.string.file_uploaded_successfully),
                        MDToast.TYPE_SUCCESS
                    )
                    onBackPressed()
                }
                Status.ERROR -> {
                    hideProgress()
                    showToast(
                        response.error?.message.toString(),
                        MDToast.TYPE_ERROR
                    )
                }
            }
        }
    }

    private fun showProgress() {
        dialog = ProgressDialog.progressDialog(this)
        dialog.show()
    }

    private fun hideProgress() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }
}