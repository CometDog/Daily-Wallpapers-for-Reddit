package me.jdowns.dailywallpapersforreddit

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