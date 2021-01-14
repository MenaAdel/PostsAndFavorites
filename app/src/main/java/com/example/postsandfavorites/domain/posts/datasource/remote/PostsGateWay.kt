package com.example.postsandfavorites.domain.posts.datasource.remote

import com.example.postsandfavorites.entites.comments.Comment
import com.example.postsandfavorites.entites.posts.PostsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsGateWay {
    @GET("posts")
    fun getPosts(): Single<List<PostsResponse>>

    @GET("posts/{post_id}/comments")
    fun getPostComments(@Path("post_id") id: Int): Single<List<Comment>>
}