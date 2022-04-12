package com.dsb.post.ui.details

import com.dsb.post.model.Comment
import com.dsb.post.model.PostWithUser

data class PostDetailsViewState(
    val showLoading: Boolean,
    val error: Throwable?,
    val commentsTitle: String,
    val postTitle: String,
    val postBody: String,
    val postAuthor: String,
    val comments: List<Comment>
) {
    val showComments: Boolean = comments.isNotEmpty() && error == null
    val showError: Boolean = error != null && comments.isEmpty()
}
