package com.dsb.post.ui.posts

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dsb.post.R
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class PostListViewStateBinding @AssistedInject constructor(
    private val resources: Resources
) {

    private val _viewState = MutableLiveData<PostListViewState>(
        PostListViewState(showContent = false, showLoading = true, error = null)
    )
    val viewState: LiveData<PostListViewState> = _viewState

    fun moveTo(state: State) {
        when (state) {
            State.Content -> PostListViewState(showLoading = false, error = null, showContent = true)
            State.Loading -> PostListViewState(showLoading = true, error = null, showContent = false)
            State.Empty -> PostListViewState(
                showLoading = false,
                error = Throwable(resources.getString(R.string.posts_screen_empty_list_message)),
                showContent = false
            )
            is State.Error -> PostListViewState(showLoading = false, error = state.error, showContent = false)
        }.also { _viewState.value = it }
    }

    sealed class State {
        object Loading : State()
        object Content : State()
        object Empty : State()
        data class Error(val error: Throwable) : State()
    }

    @AssistedFactory
    interface Factory {
        fun create(): PostListViewStateBinding
    }
}
