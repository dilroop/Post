package com.dsb.post.ui.posts

data class PostListViewState(
    val showLoading: Boolean,
    val error: Throwable?,
    val showContent: Boolean,
)
