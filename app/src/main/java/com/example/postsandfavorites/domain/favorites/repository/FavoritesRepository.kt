package com.example.postsandfavorites.domain.favorites.repository

import com.example.postsandfavorites.domain.posts.datasource.remote.PostsGateWay
import com.example.postsandfavorites.entites.comments.Comment
import io.reactivex.rxjava3.core.Single

class FavoritesRepository(private val gateWay: PostsGateWay) {

    fun postFavorites(id: Int): Single<List<Comment>> = Single.just(comments)
    val comments = listOf(
        Comment(0 ,0 ,"" ,"" ,""),
        Comment(0 ,0 ,"" ,"" ,""),
        Comment(0 ,0 ,"" ,"" ,"")
    )

}