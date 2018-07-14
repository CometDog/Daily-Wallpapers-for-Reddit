package me.jdowns.dailywallpapersforreddit

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class), (OkHttpModule::class)])
interface ApplicationComponent {
    fun inject(subredditPresenter: SubredditPresenter)
}