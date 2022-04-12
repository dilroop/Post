package com.dsb.post.ui.details

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dsb.post.R
import com.dsb.post.model.Comment
import com.dsb.post.model.PostWithUser
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class PostDetailsViewStateBinding @AssistedInject constructor(
    private val resources: Resources,
    @Assisted private val postWithUser: PostWithUser,
) {

    private val initialState = PostDetailsViewState(
        showLoading = false,
        error = null,
        commentsTitle = resources.getString(R.string.post_details_screen_comment_title),
        postTitle = postWithUser.post.title,
        postBody = postWithUser.post.body,
        postAuthor = resources.getString(R.string.post_details_screen_by_author, postWithUser.user.name),
        comments = listOf(),
    )

    private val _viewState = MutableLiveData(initialState)
    val viewState: LiveData<PostDetailsViewState> = _viewState

    fun moveTo(state: State) {
        val oldState = _viewState.value ?: initialState
        when (state) {
            State.Loading -> oldState.copy(
                showLoading = true,
                error = null,
            )
            is State.Content -> oldState.copy(
                showLoading = false,
                error = null,
                comments = state.comments
            )
            is State.Error -> oldState.copy(
                showLoading = false,
                error = state.error,
                comments = emptyList()
            )
        }.also { _viewState.value = it }
    }

    sealed class State {
        object Loading : State()
        data class Content(val comments: List<Comment>) : State()
        data class Error(val error: Throwable) : State()
    }

    @AssistedFactory
    interface Factory {
        fun create(postWithUser: PostWithUser): PostDetailsViewStateBinding
    }
}
