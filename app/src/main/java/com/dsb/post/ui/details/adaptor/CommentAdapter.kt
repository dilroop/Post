package com.dsb.post.ui.details.adaptor

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsb.post.model.Comment

class CommentAdapter(private val comments: List<Comment>) : RecyclerView.Adapter<CommentViewHolder>() {
    override fun getItemCount() = comments.count()

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.setComment(comment = comments[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder.create(parent)
    }
}