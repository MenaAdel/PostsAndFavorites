package com.example.postsandfavorites.domain.posts.datasource.remote

import com.example.postsandfavorites.entites.posts.PostsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface PostsGateWay {
    @GET("posts")
    fun getPosts(): Single<List<PostsResponse>>
}