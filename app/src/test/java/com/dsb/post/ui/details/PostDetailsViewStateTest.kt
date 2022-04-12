package com.dsb.post.ui.details

import com.dsb.post.model.Comment
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.mockito.kotlin.mock

class PostDetailsViewStateTest {
    private val comment: Comment = mock()
    private val detailsViewState = PostDetailsViewState(
        showLoading = false,
        error = null,
        commentsTitle = "",
        postTitle = "",
        postBody = "",
        postAuthor = "",
        comments = listOf(comment)
    )

    @Test
    fun `showError - no error - return false`() {
        assertThat(detailsViewState.showError).isEqualTo(false)
    }

    @Test
    fun `showError - given error - return false`() {
        val viewState = detailsViewState.copy(error = Exception("Some Error"))
        assertThat(viewState.showError).isEqualTo(true)
    }

    @Test
    fun `showComments - given comments - return true`() {
        assertThat(detailsViewState.showComments).isEqualTo(true)
    }

    @Test
    fun `showComments - given error - return false`() {
        val viewState = detailsViewState.copy(error = Exception("Some Error"))
        assertThat(viewState.showComments).isEqualTo(false)
    }
}