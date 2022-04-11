package com.dsb.post.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dsb.post.data.database.dao.CommentDao
import com.dsb.post.data.database.dao.PostDao
import com.dsb.post.data.database.dao.UserDao
import com.dsb.post.model.Comment
import com.dsb.post.model.Post
import com.dsb.post.model.User

@Database(entities = [Post::class, User::class, Comment::class], version = 1, exportSchema = false)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun userDao(): UserDao
    abstract fun commentDao(): CommentDao
}