package com.dsb.post.model

import androidx.room.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "comments_table", primaryKeys = ["postId", "id"])
@Serializable
data class Comment(
    @SerialName("postId") val postId: Int,
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
    @SerialName("body") val body: String
) {
    private fun getShortName(): String = name.split(" ").first()
    fun getUserDetails(): String {
        val shortName = getShortName()
        return when {
            shortName.isNotEmpty() && email.isNotEmpty() -> "${getShortName()} ($email)"
            shortName.isEmpty() -> email
            else -> shortName
        }
    }
}