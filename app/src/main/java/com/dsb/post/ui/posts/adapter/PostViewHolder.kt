package com.dsb.post.ui.posts.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsb.post.R
import com.dsb.post.databinding.ViewPostItemBinding
import com.dsb.post.model.PostWithUser

class PostViewHolder(private val binding: ViewPostItemBinding, private val resources: Resources) : RecyclerView.ViewHolder(binding.root) {

    fun setPostWithUser(data: PostWithUser) {
        binding.run {
            title.text = data.post.title
            subtitle.text = data.post.body
            authorName.text = resources.getString(R.string.posts_screen_by_author, data.user.name)

        }
    }

    companion object {
        fun create(viewGroup: ViewGroup): PostViewHolder {
            val inflater = LayoutInflater.from(viewGroup.context)
            val binding = ViewPostItemBinding.inflate(inflater, viewGroup, false)
            return PostViewHolder(binding, viewGroup.resources)
        }
    }
}