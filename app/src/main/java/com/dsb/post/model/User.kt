package com.dsb.post.model

import androidx.room.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "user_table")
@Serializable
data class User(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("username") val username: String,
    @SerialName("email") val email: String,
)