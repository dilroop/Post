package com.dsb.post.ui.posts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsb.post.databinding.ViewPostItemBinding
import com.dsb.post.model.Post

class PostViewHolder(private val binding: ViewPostItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun setPost(post: Post) {
        binding.run {
            title.text = post.title
            subtitle.text = post.body
        }
    }

    companion object {
        fun create(viewGroup: ViewGroup): PostViewHolder {
            val inflater = LayoutInflater.from(viewGroup.context)
            val binding = ViewPostItemBinding.inflate(inflater, viewGroup, false)
            return PostViewHolder(binding)
        }
    }
}