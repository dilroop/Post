package com.dsb.post.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "post_table")
@Serializable
data class Post(
    @PrimaryKey
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("body") val body: String,
    @SerialName("userId") val userId: Int
)