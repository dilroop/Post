package com.dsb.post.ui.details

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsb.post.model.commentSample
import com.dsb.post.model.postWithUserSample
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import org.junit.Rule
import org.junit.Test

class PostDetailsViewStateBindingTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val resources: Resources = mock {
        on { getString(any()) }.thenReturn("Comments")
        on { getString(any(), any()) }.thenReturn("John Doe")
    }

    private val binding = PostDetailsViewStateBinding(
        resources = resources,
        postWithUser = postWithUserSample
    )

    @Test
    fun `initial binding - check value`() {
        binding.viewState.value?.let { viewState ->
            assertThat(viewState.comments).isEmpty()
            assertThat(viewState.showComments).isFalse()
            assertThat(viewState.showLoading).isFalse()
            assertThat(viewState.error).isNull()
            assertThat(viewState.commentsTitle).isEqualTo("Comments")
            assertThat(viewState.postTitle).isEqualTo("Title")
            assertThat(viewState.postBody).isEqualTo("this is the post body")
            assertThat(viewState.postAuthor).isEqualTo("John Doe")
            assertThat(viewState.showError).isFalse()
        }
    }

    @Test
    fun `moveTo - loading state - check value`() {
        binding.moveTo(PostDetailsViewStateBinding.State.Loading)
        binding.viewState.value?.let { viewState ->
            assertThat(viewState.comments).isEmpty()
            assertThat(viewState.showComments).isFalse()
            assertThat(viewState.showLoading).isTrue()
            assertThat(viewState.error).isNull()
            assertThat(viewState.commentsTitle).isEqualTo("Comments")
            assertThat(viewState.postTitle).isEqualTo("Title")
            assertThat(viewState.postBody).isEqualTo("this is the post body")
            assertThat(viewState.postAuthor).isEqualTo("John Doe")
            assertThat(viewState.showError).isFalse()
        }
    }

    @Test
    fun `moveTo - content state - check value`() {
        val comments = listOf(commentSample)
        binding.moveTo(PostDetailsViewStateBinding.State.Content(comments = listOf(commentSample)))
        binding.viewState.value?.let { viewState ->
            assertThat(viewState.comments).isEqualTo(comments)
            assertThat(viewState.showComments).isTrue()
            assertThat(viewState.showLoading).isFalse()
            assertThat(viewState.error).isNull()
            assertThat(viewState.commentsTitle).isEqualTo("Comments")
            assertThat(viewState.postTitle).isEqualTo("Title")
            assertThat(viewState.postBody).isEqualTo("this is the post body")
            assertThat(viewState.postAuthor).isEqualTo("John Doe")
            assertThat(viewState.showError).isFalse()
        }
    }

    @Test
    fun `moveTo - error state - check value`() {
        val error = Exception("Some Error")
        binding.moveTo(PostDetailsViewStateBinding.State.Error(error))
        binding.viewState.value?.let { viewState ->
            assertThat(viewState.comments).isEmpty()
            assertThat(viewState.showComments).isFalse()
            assertThat(viewState.showLoading).isFalse()
            assertThat(viewState.error).isEqualTo(error)
            assertThat(viewState.commentsTitle).isEqualTo("Comments")
            assertThat(viewState.postTitle).isEqualTo("Title")
            assertThat(viewState.postBody).isEqualTo("this is the post body")
            assertThat(viewState.postAuthor).isEqualTo("John Doe")
            assertThat(viewState.showError).isTrue()
        }
    }
}
