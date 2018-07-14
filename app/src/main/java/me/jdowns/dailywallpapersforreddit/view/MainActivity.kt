package me.jdowns.dailywallpapersforreddit.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import me.jdowns.dailywallpapersforreddit.R
import me.jdowns.dailywallpapersforreddit.navigator.MainNavigator

class MainActivity : AppCompatActivity() {
    private lateinit var navigator: MainNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator = MainNavigator(this)
        navigator.showMainFragment()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            navigator.showSettingsFragment()
            true
        }
        android.R.id.home -> {
            navigator.back()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
