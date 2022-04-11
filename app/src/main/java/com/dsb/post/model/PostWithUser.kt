package com.dsb.post.model

import androidx.room.Embedded
import androidx.room.Relation

data class PostWithUser(
    @Embedded
    val post: Post,

    @Relation(parentColumn = "userId", entityColumn = "id")
    val user: User
)
