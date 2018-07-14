package me.jdowns.dailywallpapersforreddit

import android.app.Application
import me.jdowns.dailywallpapersforreddit.dagger.ApplicationComponent
import me.jdowns.dailywallpapersforreddit.dagger.modules.ApplicationModule
import me.jdowns.dailywallpapersforreddit.dagger.modules.OkHttpModule

class DailyWallpapers : Application() {
    override fun onCreate() {
        super.onCreate()
        dependencyGraph = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .okHttpModule(OkHttpModule())
            .build()
    }

    companion object {
        @JvmStatic
        lateinit var dependencyGraph: ApplicationComponent
    }
}