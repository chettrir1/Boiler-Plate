package com.iions.done.feature.main.screens

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.iions.SharedPreferenceManager
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityMainBinding
import com.iions.done.feature.main.screens.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(),
    NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var sharedPreferenceManager: SharedPreferenceManager
    private val viewModel: MainViewModel by viewModels()
    private lateinit var dialog: Dialog

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            activity.startActivity(intent)
            activity.finish()
        }
    }

    override fun layout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.includeToolbar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(false)
        setDefaultFragment(savedInstanceState)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    val fragment = HomeFragment.getInstance()
                    addFragment(fragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    override fun initObservers() {
    }

    private fun setDefaultFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.mainContainer,
                    HomeFragment.getInstance()
                ).commit()
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    override fun onBackPressed() {
        when {
            binding.drawerLayout.isDrawerOpen(GravityCompat.START) -> {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            else -> {
                if (binding.bottomNavigationView.selectedItemId == R.id.action_home) {
                    super.onBackPressed()
                    finish()
                } else {
                    binding.bottomNavigationView.selectedItemId = R.id.action_home
                }
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_home -> {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            else -> {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
        return true
    }
}