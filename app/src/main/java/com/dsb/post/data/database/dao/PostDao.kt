package com.dsb.post.data.database.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.dsb.post.model.Post
import com.dsb.post.model.PostWithUser

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<Post>)

    @Query("SELECT * FROM post_table")
    fun getPost(): PagingSource<Int, Post>

    @Transaction
    @Query("SELECT * FROM post_table")
    fun getPostWithUser(): PagingSource<Int, PostWithUser>

    @Query("SELECT COUNT(*) FROM post_table")
    suspend fun countPost(): Int
}