package com.dsb.post.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsb.post.model.Comment

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(comments: List<Comment>)

    @Query("SELECT * FROM comments_table WHERE postId = :postId")
    fun getCommentsOfPost(postId: Int): List<Comment>?
}