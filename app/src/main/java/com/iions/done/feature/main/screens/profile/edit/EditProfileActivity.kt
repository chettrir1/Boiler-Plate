package com.iions.done.feature.main.screens.profile.edit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityEditProfileBinding
import com.iions.done.databinding.ActivityMainBinding
import com.iions.done.feature.main.screens.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileActivity : BaseActivity<ActivityEditProfileBinding>() {
    override fun layout(): Int = R.layout.activity_edit_profile

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, EditProfileActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun initObservers() {
    }
}