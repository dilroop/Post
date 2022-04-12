package com.dsb.post.ui.posts

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsb.post.ui.posts.PostListViewStateBinding.State
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import org.junit.Rule
import org.junit.Test

class PostListViewStateBindingTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val resources: Resources = mock {
        on { getString(any()) }.thenReturn("Comments")
        on { getString(any(), any()) }.thenReturn("John Doe")
    }
    private val binding = PostListViewStateBinding(resources = resources)

    @Test
    fun `initial binding - check value`() {
        binding.viewState.value?.let { viewState ->
            assertThat(viewState.error).isNull()
            assertThat(viewState.showContent).isFalse()
            assertThat(viewState.showLoading).isTrue()
        }
    }

    @Test
    fun `moveTo - loading state - check value`() {
        binding.moveTo(State.Loading)
        binding.viewState.value?.let { viewState ->
            assertThat(viewState.error).isNull()
            assertThat(viewState.showContent).isFalse()
            assertThat(viewState.showLoading).isTrue()
        }
    }

    @Test
    fun `moveTo - content state - check value`() {
        binding.moveTo(State.Content)
        binding.viewState.value?.let { viewState ->
            assertThat(viewState.error).isNull()
            assertThat(viewState.showContent).isTrue()
            assertThat(viewState.showLoading).isFalse()
        }
    }

    @Test
    fun `moveTo - error state - check value`() {
        val exception = Exception("Some Error Occurred")
        binding.moveTo(State.Error(exception))
        binding.viewState.value?.let { viewState ->
            assertThat(viewState.error).isEqualTo(exception)
            assertThat(viewState.showContent).isFalse()
            assertThat(viewState.showLoading).isFalse()
        }
    }
}