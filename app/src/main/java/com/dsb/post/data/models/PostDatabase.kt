package com.dsb.post.data.models

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dsb.post.model.Post

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class PostDatabase : RoomDatabase() {
    abstract fun userDao(): PostDao
}