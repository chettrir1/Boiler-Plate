package com.iions.appname.feature.main.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.iions.SharedPreferenceManager
import com.iions.appname.R
import com.iions.appname.base.BaseActivity
import com.iions.appname.databinding.ActivityMainBinding
import com.iions.appname.feature.main.screens.cart.CartFragment
import com.iions.appname.feature.main.screens.favourite.FavouriteFragment
import com.iions.appname.feature.main.screens.home.HomeFragment
import com.iions.appname.feature.main.screens.profile.ProfileFragment
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
        }
    }

    override fun layout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        //        if (!(viewModel.isUserLoggedIn())) {
//            startActivity(LoginActivity.start(this))
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
                R.id.action_favourite -> {
                    val fragment = FavouriteFragment.getInstance()
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
        binding.drawerLayout.closeDrawer(GravityCompat.START)
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
//        binding.drawerLayout.useCustomBehavior(Gravity.START)
    }

}