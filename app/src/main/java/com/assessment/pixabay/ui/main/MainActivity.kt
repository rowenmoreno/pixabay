package com.assessment.pixabay.ui.main

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.assessment.pixabay.BaseActivity
import com.assessment.pixabay.Constants
import com.assessment.pixabay.R
import com.assessment.pixabay.databinding.ActivityMainBinding
import com.assessment.pixabay.ui.main.adapters.ImagesAdapter
import com.google.android.material.navigation.NavigationView


class MainActivity : BaseActivity<ActivityMainBinding>(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toggle: ActionBarDrawerToggle
    lateinit var homeFragment: HomeFragment
    lateinit var imagesFragment: ImagesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDrawer()
        setupOnClicks()
        homeFragment = HomeFragment()
        addFragment(homeFragment, R.id.container)
    }


    override fun onResume() {
        super.onResume()

    }

    private fun setupOnClicks() {
        binding.add.setOnClickListener(View.OnClickListener {
            val f = supportFragmentManager.findFragmentById(R.id.container)
            if (f is ImagesFragment) {
                if (!this::homeFragment.isInitialized)
                    homeFragment = HomeFragment()
                replaceFragment(homeFragment, R.id.container)
            }else {
                if (!this::imagesFragment.isInitialized)
                    imagesFragment = ImagesFragment()
                replaceFragment(imagesFragment, R.id.container)
            }
        })
    }

    private fun initDrawer() {
        setSupportActionBar(binding.toolbarMain)
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbarMain, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                Toast.makeText(applicationContext, "Home", Toast.LENGTH_SHORT).show()
            }
            R.id.cart -> {
                Toast.makeText(applicationContext, "My Cart", Toast.LENGTH_SHORT).show()
            }
            R.id.orders -> {
                Toast.makeText(applicationContext, "Orders", Toast.LENGTH_SHORT).show()
            }
            R.id.profile -> {
                Toast.makeText(applicationContext, "Profile", Toast.LENGTH_SHORT).show()
            }
            R.id.settings -> {
                Toast.makeText(applicationContext, "Settings", Toast.LENGTH_SHORT).show()}
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }





}