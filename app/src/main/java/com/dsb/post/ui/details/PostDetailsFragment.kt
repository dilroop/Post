package com.dsb.post.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dsb.post.databinding.PostDetailsFragmentBinding
import com.dsb.post.ui.details.adaptor.CommentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailsFragment : Fragment() {

    private val viewModel: PostDetailsViewModel by viewModels()
    private lateinit var binding: PostDetailsFragmentBinding
    private lateinit var commentAdapter: CommentAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = PostDetailsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner) {
            binding.viewState = it

            if (it.comments.isNotEmpty()) {
                binding.recyclerView.adapter = CommentAdapter(it.comments)
            }
        }

    }
}