package com.chugunov.phonewalls.presentation

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.chugunov.phonewalls.R
import com.chugunov.phonewalls.presentation.MainActivity.Companion.LANGUAGE_KEY
import com.chugunov.phonewalls.presentation.MainActivity.Companion.THEME_KEY
import java.util.*

class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var languagePreference: ListPreference
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var themePreference: ListPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        languagePreference = findPreference(LANGUAGE_KEY)!!
        themePreference = findPreference(THEME_KEY)!!
        languagePreference.summary = getLanguageSummary(languagePreference.value)
        themePreference.summary = getThemeSummary(themePreference.value)


        languagePreference.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, newValue ->
                setLocale(newValue as String)
                languagePreference.summary = getLanguageSummary(newValue)
                true
            }

        themePreference.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, newValue ->
                setTheme(newValue as String)
                themePreference.summary = getThemeSummary(newValue)
                true
            }
    }


    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        if (key == LANGUAGE_KEY) {
            val language = sharedPreferences.getString(key, "")
            setLocale(language)
            languagePreference.summary = getLanguageSummary(language)
        }
    }

    private fun setLocale(LANGUAGE_KEY: String?) {
        if (!LANGUAGE_KEY.isNullOrEmpty()) {
            val locale = Locale(LANGUAGE_KEY)
            Locale.setDefault(locale)

            val configuration = resources.configuration
            configuration.setLocale(locale)

            val editor = PreferenceManager.getDefaultSharedPreferences(requireContext()).edit()
            editor.putString(LANGUAGE_KEY, "")
            editor.apply()

            requireActivity().apply {
                baseContext.resources.updateConfiguration(
                    configuration,
                    baseContext.resources.displayMetrics
                )
                recreate()
            }
        }
    }

    private fun setTheme(theme: String?) {
        when(theme) {
            "light" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            "dark" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "default" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }

    private fun getLanguageSummary(language: String?): CharSequence? {
        return when (language) {
            "en" -> getString(R.string.language_english)
            "ru" -> getString(R.string.language_russian)
            else -> null
        }
    }

    private fun getThemeSummary(theme: String?): CharSequence? {
        return when (theme) {
            "light" -> getString(R.string.theme_light)
            "dark" -> getString(R.string.theme_dark)
            "default" -> getString(R.string.theme_default)
            else -> null
        }
    }
}