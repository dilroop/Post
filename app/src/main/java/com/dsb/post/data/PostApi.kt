package com.dsb.post.data

import com.dsb.post.model.Post
import retrofit2.http.GET

interface PostApi {

    @GET("posts")
    suspend fun getPosts(): List<Post>
}