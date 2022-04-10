package com.dsb.post.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "post_table")
@Serializable
data class Post(
    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
)
