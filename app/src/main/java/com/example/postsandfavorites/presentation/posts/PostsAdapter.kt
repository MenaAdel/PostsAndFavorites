package com.example.postsandfavorites.presentation.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.postsandfavorites.R
import com.example.postsandfavorites.entites.posts.PostsResponse
import kotlinx.android.synthetic.main.item_posts.view.*

class PostsAdapter(
    private val list: List<PostsResponse>,
    private val action: (Int) -> Unit
) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    class PostsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.title_text
        val descriptionText: TextView = view.description_text
        val cardLayout: CardView = view.layout_card
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder =
        parent.context
            .let { LayoutInflater.from(it) }
            .inflate(R.layout.item_posts, parent, false)
            .let { PostsViewHolder(it) }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val item = list[position]
        with(holder) {
            titleText.text = item.title
            descriptionText.text = item.body
            cardLayout.setOnClickListener {
                action(item.id)
            }
        }
    }

    override fun getItemCount(): Int = list.size
}