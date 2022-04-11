package com.dsb.post.ui.posts.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.dsb.post.model.PostWithUser

class PostAdapter : PagingDataAdapter<PostWithUser, PostViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        getItem(position)?.let {
            holder.setPostWithUser(data = it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.create(parent)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<PostWithUser>() {
            override fun areItemsTheSame(oldItem: PostWithUser, newItem: PostWithUser): Boolean {
                return oldItem.post.id == newItem.post.id && oldItem.user.id == newItem.user.id
            }

            override fun areContentsTheSame(oldItem: PostWithUser, newItem: PostWithUser) = oldItem == newItem
        }
    }
}