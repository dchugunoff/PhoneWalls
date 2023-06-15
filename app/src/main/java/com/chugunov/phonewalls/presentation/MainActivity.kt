package com.chugunov.phonewalls.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.chugunov.phonewalls.R
import com.chugunov.phonewalls.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNavView = binding.bottomNavView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.categorySelectionFragment -> {
                    bottomNavView.menu.findItem(R.id.menu_item_home).isChecked = true
                }
                R.id.imagesFragment -> {
                    bottomNavView.menu.findItem(R.id.menu_item_home).isChecked = true
                }
                else -> {
                    bottomNavView.menu.findItem(destination.id)?.isChecked = true
                }
            }
        }

        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_item_settings -> {
                    navController.navigate(R.id.settingsFragment2)
                    true
                }
                R.id.menu_item_home -> {
                    navController.popBackStack()
                    navController.navigate(R.id.categorySelectionFragment)
                    true
                }
                else -> false
            }
        }

    }
    private fun getCurrentFragment(): Fragment? {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        return navHostFragment.childFragmentManager.fragments.firstOrNull()
    }
}