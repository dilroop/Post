package com.dsb.post.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dsb.post.data.PostRepository
import com.dsb.post.model.Post
import com.dsb.post.model.PostWithUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class PostListViewModel @Inject constructor(
    private val repository: PostRepository,
    viewStateBindingFactory: PostListViewStateBinding.Factory
) : ViewModel() {
    private val viewStateBinding =  viewStateBindingFactory.create()
    val binding = viewStateBinding.viewState
    val posts: Flow<PagingData<PostWithUser>> by lazy {
        datasource.cachedIn(viewModelScope)
    }

    private val datasource = repository.getPosts()

    fun setState(state: PostListViewStateBinding.State) {
        viewStateBinding.moveTo(state)
    }
}