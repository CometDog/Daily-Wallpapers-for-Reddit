package me.jdowns.dailywallpapersforreddit.navigator

import me.jdowns.dailywallpapersforreddit.R
import me.jdowns.dailywallpapersforreddit.view.MainActivity
import me.jdowns.dailywallpapersforreddit.view.SettingsFragment
import me.jdowns.dailywallpapersforreddit.view.SubredditFragment

class MainNavigator(private val view: MainActivity) {
    fun showMainFragment() {
        view.supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.main_fragment,
                SubredditFragment(),
                SubredditFragment.FRAGMENT_TAG
            )
            .commit()
    }

    fun showSettingsFragment() {
        view.supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.main_fragment,
                SettingsFragment(),
                SettingsFragment.FRAGMENT_TAG
            )
            .addToBackStack(SubredditFragment.FRAGMENT_TAG)
            .commit()
    }

    fun back() {
        view.supportFragmentManager.popBackStack()
    }
}