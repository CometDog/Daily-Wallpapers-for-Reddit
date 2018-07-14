package me.jdowns.dailywallpapersforreddit.model

import com.squareup.moshi.Json

class PostsModel {
    @field:Json(name = "data")
    var data: PostsDataModel? = null

    class PostsDataModel {
        @field:Json(name = "children")
        var posts: List<PostModel>? = null

        class PostModel {
            @field:Json(name = "kind")
            var kind: String? = null

            @field:Json(name = "data")
            var data: PostDataModel? = null

            class PostDataModel {
                @field:Json(name = "name")
                var id: String? = null

                @field:Json(name = "post_hint")
                var hint: String? = null

                @field:Json(name = "over_18")
                var nsfw: Boolean = true

                @field:Json(name = "url")
                var url: String? = null
            }
        }
    }
}