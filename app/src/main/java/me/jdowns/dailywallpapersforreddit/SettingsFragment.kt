package me.jdowns.dailywallpapersforreddit

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, key: String?) {
        setPreferencesFromResource(R.xml.activity_settings, key)
    }

    companion object {
        const val FRAGMENT_TAG = "settingsFragment"
    }
}
