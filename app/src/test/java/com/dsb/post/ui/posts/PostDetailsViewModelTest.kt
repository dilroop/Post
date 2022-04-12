package com.dsb.post.ui.posts

import androidx.paging.ExperimentalPagingApi
import com.dsb.post.MainDispatcherRule
import com.dsb.post.data.PostRepository
import com.dsb.post.model.commentSample
import com.dsb.post.ui.posts.PostListViewStateBinding.State
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test

@ExperimentalPagingApi
class PostListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val postRepository: PostRepository = mock {
        onBlocking { getCommentsOnPost(any()) }.thenReturn(listOf(commentSample))
    }
    private val viewStateBinding: PostListViewStateBinding = mock()//PostListViewStateBinding(resources)
    private val factory: PostListViewStateBinding.Factory = mock {
        on { create() }.thenReturn(viewStateBinding)
    }
    private val viewModel by lazy {
        PostListViewModel(
            repository = postRepository,
            viewStateBindingFactory = factory
        )
    }

    @Test
    fun `viewModel - verify binding factory create`() {
        viewModel
        verify(factory).create()
    }

    @Test
    fun `setState - loading - verify viewModel ask binding to update state`() {
        viewModel.setState(State.Loading)
        verify(viewStateBinding).moveTo(State.Loading)
    }

    @Test
    fun `setState - content - verify viewModel ask binding to update state`() {
        viewModel.setState(State.Content)
        verify(viewStateBinding).moveTo(State.Content)
    }

    @Test
    fun `setState - error - verify viewModel ask binding to update state`() {
        val exception = Exception("some error")
        viewModel.setState(State.Error(exception))
        verify(viewStateBinding).moveTo(State.Error(exception))
    }

    @Test
    fun `setState - empty - verify viewModel ask binding to update state`() {
        viewModel.setState(State.Empty)
        verify(viewStateBinding).moveTo(State.Empty)
    }
}