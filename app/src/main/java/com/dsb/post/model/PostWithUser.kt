package com.dsb.post.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class PostWithUser(
    @Embedded
    val post: Post,

    @Relation(parentColumn = "userId", entityColumn = "id")
    val user: User
): Parcelable
