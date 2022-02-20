package com.iions.done.feature.main.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.iions.SharedPreferenceManager
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivityMainBinding
import com.iions.done.feature.auth.screens.login.LoginActivity
import com.iions.done.feature.main.screens.camera.CameraFragment
import com.iions.done.feature.main.screens.cart.CartFragment
import com.iions.done.feature.main.screens.history.HistoryFragment
import com.iions.done.feature.main.screens.home.HomeFragment
import com.iions.done.feature.main.screens.profile.ProfileFragment
import com.iions.done.utils.alertdialog.showAlert
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.toolbar_main.view.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(),
    NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var sharedPreferenceManager: SharedPreferenceManager
    private val viewModel: MainViewModel by viewModels()

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }

    override fun layout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (!(viewModel.isUserLoggedIn())) {
//            LoginActivity.start(this)
//            finish()
//            return
//        }
        setUpNavigationDrawer()
        setSupportActionBar(binding.includeToolbar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(false)
        setDefaultFragment(savedInstanceState)
        binding.includeToolbar.ivMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    val fragment = HomeFragment.getInstance()
                    addFragment(fragment)
                    return@setOnItemSelectedListener true
                }
                R.id.action_history -> {
                    val fragment = HistoryFragment.getInstance()
                    addFragment(fragment)
                    return@setOnItemSelectedListener true
                }
                R.id.action_camera -> {
                    val fragment = CameraFragment.getInstance()
                    addFragment(fragment)
                    return@setOnItemSelectedListener true
                }
                R.id.action_cart -> {
                    val fragment = CartFragment.getInstance()
                    addFragment(fragment)
                    return@setOnItemSelectedListener true
                }
                R.id.action_profile -> {
                    val fragment = ProfileFragment.getInstance()
                    addFragment(fragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
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

    override fun initObservers() {
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_home -> {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

            R.id.action_logout -> {
                showAlertDialog(
                    getString(R.string.alert),
                    getString(R.string.are_you_sure_you_want_to_logout)
                )
            }
            else -> {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
        return true
    }

    private fun setUpNavigationDrawer() {
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.includeToolbar.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navigationView.setNavigationItemSelectedListener(this)
    }

    private fun showAlertDialog(title: String, message: String) {
        showAlert(
            title = title,
            message = message,
            imageRes = null,
            buttons = arrayOf(getString(R.string.logout), getString(R.string.cancel)),
            handler = { index ->
                when (index) {
                    0 -> {
                    }
                    else -> Unit
                }
            }
        )
    }
}