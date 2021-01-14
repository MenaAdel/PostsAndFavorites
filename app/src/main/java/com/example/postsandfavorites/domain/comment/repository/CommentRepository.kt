package com.example.postsandfavorites.domain.comment.repository

import com.example.postsandfavorites.domain.posts.datasource.remote.PostsGateWay
import com.example.postsandfavorites.entites.comments.Comment
import io.reactivex.rxjava3.core.Single

class CommentRepository (private val gateWay: PostsGateWay) {
    fun getComments(postId: Int): Single<List<Comment>> = gateWay.getPostComments(postId)

}