package com.example.postsandfavorites.presentation.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.postsandfavorites.R
import com.example.postsandfavorites.entites.comments.Comment
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentAdapter(
    private val list: List<Comment>
) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.name_text
        val descriptionText: TextView = view.body_text
        val email: TextView = view.email_text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder =
        parent.context
            .let { LayoutInflater.from(it) }
            .inflate(R.layout.item_comment, parent, false)
            .let { CommentViewHolder(it) }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item = list[position]
        with(holder) {
            titleText.text = item.title
            descriptionText.text = item.body
            email.text = item.email
        }
    }

    override fun getItemCount(): Int = list.size
}