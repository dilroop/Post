package com.dsb.post.data

import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {

    @GET("posts")
    suspend fun getPosts(): List<String>
}