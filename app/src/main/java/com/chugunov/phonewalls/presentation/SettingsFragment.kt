package com.chugunov.phonewalls.presentation

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.chugunov.phonewalls.R
import java.util.*

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var languagePreference: ListPreference
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        languagePreference = findPreference(getString(R.string.language_key))!!
        languagePreference.summary = getLanguageSummary(languagePreference.value)

        languagePreference.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
            setLocale(newValue as String)
            languagePreference.summary = getLanguageSummary(newValue)
            true
        }
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        if (key == getString(R.string.language_key)) {
            val language = sharedPreferences.getString(key, "")
            setLocale(language)
            languagePreference.summary = getLanguageSummary(language)
        }
    }

    private fun setLocale(language: String?) {
        if (!language.isNullOrEmpty()) {
            val locale = Locale(language)
            Locale.setDefault(locale)

            val configuration = resources.configuration
            configuration.setLocale(locale)

            val editor = PreferenceManager.getDefaultSharedPreferences(requireContext()).edit()
            editor.putString(getString(R.string.language_key), language)
            editor.apply()

            val context = requireContext().createConfigurationContext(configuration)
            requireActivity().apply {
                baseContext.resources.updateConfiguration(configuration, baseContext.resources.displayMetrics)
                recreate()
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
}