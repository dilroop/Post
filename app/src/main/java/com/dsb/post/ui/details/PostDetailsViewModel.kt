package com.dsb.post.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsb.post.data.PostRepository
import com.dsb.post.model.PostWithUser
import com.dsb.post.ui.details.PostDetailsViewStateBinding.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val repository: PostRepository,
    savedStateHandle: SavedStateHandle,
    viewStateBindingFactory: PostDetailsViewStateBinding.Factory
) : ViewModel() {
    companion object {
        private const val ARGUMENT_KEY = "postWithUser"
    }

    private val arguments = savedStateHandle.get<PostWithUser>(ARGUMENT_KEY) ?: throw IllegalArgumentException("Expected Correct Argument")
    private val viewStateBinding = viewStateBindingFactory.create(arguments)
    val viewState = viewStateBinding.viewState

    init {
        loadComments()
    }

    private fun loadComments() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setState(State.Loading)
                try {
                    val comments = repository.getCommentsOnPost(arguments.post.id)
                    setState(State.Content(comments = comments))
                } catch (exception: Exception) {
                    setState(State.Error(exception))
                }
            }
        }
    }

    private suspend fun setState(state: State) = withContext(Dispatchers.Main) { viewStateBinding.moveTo(state) }
}