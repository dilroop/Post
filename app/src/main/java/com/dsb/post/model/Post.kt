package com.dsb.post.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class Post(
    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
)
