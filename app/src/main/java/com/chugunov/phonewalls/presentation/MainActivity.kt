package com.chugunov.phonewalls.presentation

import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.PreferenceManager
import com.chugunov.phonewalls.R
import com.chugunov.phonewalls.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val language = sharedPreferences.getString("language", "")
        setLocale(language)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        bottomNavView = binding.bottomNavView

        Log.d("Prefer", "${sharedPreferences.getString("language", "")}")
        Log.d("Prefer", "${sharedPreferences.contains("language")}")
        Log.d("Prefer", "${language}")
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

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val language = sharedPreferences.getString(getString(R.string.language_key), "")
        setLocale(language)
        recreate()
    }

    private fun setLocale(language: String?) {
        if (!language.isNullOrEmpty()) {
            val locale = Locale(language)
            Locale.setDefault(locale)

            val resources = resources
            val configuration = resources.configuration
            configuration.setLocale(locale)

            createConfigurationContext(configuration)
            resources.updateConfiguration(configuration, resources.displayMetrics)
            Log.d("Prefer", "1")
        }
    }
}
