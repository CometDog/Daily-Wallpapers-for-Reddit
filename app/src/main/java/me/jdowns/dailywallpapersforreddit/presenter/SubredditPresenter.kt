package me.jdowns.dailywallpapersforreddit.presenter

import android.app.WallpaperManager
import android.content.Context
import android.support.annotation.WorkerThread
import com.squareup.moshi.Moshi
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import me.jdowns.dailywallpapersforreddit.DailyWallpapers
import me.jdowns.dailywallpapersforreddit.model.PostsModel
import me.jdowns.dailywallpapersforreddit.view.SubredditFragment
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.net.URL
import javax.inject.Inject

class SubredditPresenter(private val view: SubredditFragment) {
    @Inject
    internal lateinit var applicationContext: Context
    @Inject
    internal lateinit var okHttpClient: OkHttpClient

    init {
        DailyWallpapers.dependencyGraph.inject(this)
    }

    @WorkerThread
    fun requestPost(subreddit: String) {
        okHttpClient.newCall(
            Request.Builder()
                .url("https://www.reddit.com/r/$subreddit/best/.json")
                .build()
        )
            .execute()?.let {
                buildPostsModel(it)
            } ?: onError()
    }

    private fun buildPostsModel(response: Response) {
        Moshi.Builder().build().adapter(PostsModel::class.java).fromJson(response.body()!!.source())?.let {
            it.data?.posts?.let {
                findValidPost(it)
            } ?: onError()
        } ?: onError()
    }

    private fun findValidPost(posts: List<PostsModel.PostsDataModel.PostModel>) {
        var postFound = false
        val lastSetPostId = getLastSetPost()
        for (post in posts) {
            if (post.kind == "t3") {
                if (post.data != null) {
                    if (post.data!!.id != lastSetPostId
                        && post.data!!.hint == "image"
                        && (!post.data!!.nsfw || getAllowNsfw())
                        && post.data!!.url != null
                    ) {
                        setWallpaper(post.data!!.url!!)
                        saveSetWallpaper(post.data!!.id ?: "")
                        postFound = true
                        break
                    }
                }
            }
        }
        if (!postFound) {
            onError()
        }
    }

    @WorkerThread
    private fun setWallpaper(url: String) {
        val connection = URL(url).openConnection()
        connection.connect()
        WallpaperManager.getInstance(view.context).setStream(connection.getInputStream())
    }

    private fun getAllowNsfw(): Boolean = applicationContext.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
        .getBoolean("ALLOW_NSFW", false)

    private fun getLastSetPost(): String = applicationContext.getSharedPreferences("HISTORY", Context.MODE_PRIVATE)
        .getString("LAST_SET_POST", "")

    private fun saveSetWallpaper(id: String) = applicationContext.getSharedPreferences("HISTORY", Context.MODE_PRIVATE)
        .edit().putString("LAST_SET_POST", id)
        .apply()

    private fun onError() {
        launch(UI) {
            view.showError()
        }
    }
}