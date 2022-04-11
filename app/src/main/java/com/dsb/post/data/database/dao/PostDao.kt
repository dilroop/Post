package com.dsb.post.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsb.post.model.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<Post>)

    @Query("SELECT * FROM post_table")
    fun getPost(): PagingSource<Int, Post>

    @Query("SELECT COUNT(*) FROM post_table")
    suspend fun countPost(): Int
}