package me.jdowns.dailywallpapersforreddit.dagger

import dagger.Component
import me.jdowns.dailywallpapersforreddit.dagger.modules.ApplicationModule
import me.jdowns.dailywallpapersforreddit.dagger.modules.OkHttpModule
import me.jdowns.dailywallpapersforreddit.presenter.SubredditPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class), (OkHttpModule::class)])
interface ApplicationComponent {
    fun inject(subredditPresenter: SubredditPresenter)
}