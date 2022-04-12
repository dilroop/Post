package com.dsb.post.ui.details.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsb.post.databinding.ViewCommentItemBinding
import com.dsb.post.model.Comment

class CommentViewHolder(private val binding: ViewCommentItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setComment(comment: Comment) {
        binding.userDetails.text = comment.getUserDetails()
        binding.comment.text = comment.body
    }

    companion object {
        fun create(viewGroup: ViewGroup): CommentViewHolder {
            val inflater = LayoutInflater.from(viewGroup.context)
            val binding = ViewCommentItemBinding.inflate(inflater, viewGroup, false)
            return CommentViewHolder(binding)
        }
    }
}