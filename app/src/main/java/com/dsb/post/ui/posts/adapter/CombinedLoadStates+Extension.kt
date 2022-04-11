package com.dsb.post.ui.posts.adapter

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView

inline fun <A : Any, B : RecyclerView.ViewHolder> CombinedLoadStates.decideOnState(
    adapter: PagingDataAdapter<A, B>,
    showContent: () -> Unit,
    showLoading: () -> Unit,
    showEmptyState: () -> Unit,
    showError: (Throwable) -> Unit
) {

    if (mediator?.refresh is LoadState.Loading) showLoading()

    if (source.refresh is LoadState.NotLoading && adapter.itemCount == 0) showEmptyState()

    if (source.refresh is LoadState.NotLoading || mediator?.refresh is LoadState.NotLoading) showContent()

    val errorState = source.append as? LoadState.Error
        ?: source.prepend as? LoadState.Error
        ?: source.refresh as? LoadState.Error
        ?: append as? LoadState.Error
        ?: prepend as? LoadState.Error
        ?: refresh as? LoadState.Error

    errorState?.let {
        showError(it.error)
    }
}