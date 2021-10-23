package com.example.rk1.settings

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.rk1.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val editPref = preferenceScreen.findPreference("days")

        editPref.onPreferenceChangeListener = object: Preference.OnPreferenceChangeListener {
            override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
                try {
                    val parsed = newValue.toString().toInt()
                    if (parsed > 0) {
                        return true
                    }
                } catch (e : Exception) {
                    return false
                }
                return false

            }
        }

    }
}