package com.dsb.post.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import com.dsb.post.databinding.PostListFragmentBinding
import com.dsb.post.model.PostWithUser
import com.dsb.post.ui.posts.PostListViewStateBinding.State
import com.dsb.post.ui.posts.adapter.PostAdapter
import com.dsb.post.ui.posts.adapter.PostViewHolder
import com.dsb.post.ui.posts.adapter.decideOnState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalPagingApi
@AndroidEntryPoint
class PostListFragment : Fragment() {
    private val viewModel: PostListViewModel by viewModels()
    private lateinit var binding: PostListFragmentBinding

    private val adapter = PostAdapter { postId -> navigateToDetailScreen(postId) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = PostListFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter

        viewModel.binding.observe(viewLifecycleOwner) {
            binding.viewState = it
        }

        lifecycleScope.launch {
            adapter.addLoadStateListener {
                it.decideOnState(
                    adapter,
                    showLoading = { viewModel.setState(State.Loading)},
                    showEmptyState = { viewModel.setState(State.Empty)},
                    showError = { throwable -> viewModel.setState(State.Error(throwable))},
                    showContent = { viewModel.setState(State.Content)},
                )
            }
            viewModel.posts.collectLatest { data ->
                adapter.submitData(data)
            }
        }
    }

    private fun navigateToDetailScreen(postId: Int) {
        findNavController().navigate(
            PostListFragmentDirections.navigateToDetailScreen(postId = postId)
        )
    }
}