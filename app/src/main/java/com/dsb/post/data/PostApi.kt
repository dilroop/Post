package com.dsb.post.data

import com.dsb.post.model.Comment
import com.dsb.post.model.Post
import com.dsb.post.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("users")
    suspend fun getAllUsers(): List<User>

    @GET("post/{id}/comments")
    suspend fun getPostComments(@Path("id") postId: Int): List<Comment>
}