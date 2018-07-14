package me.jdowns.dailywallpapersforreddit

import android.app.Application

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