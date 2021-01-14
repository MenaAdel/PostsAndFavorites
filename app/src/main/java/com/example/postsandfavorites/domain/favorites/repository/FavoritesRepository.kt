package com.example.postsandfavorites.domain.favorites.repository

import com.example.postsandfavorites.domain.posts.datasource.remote.PostsGateWay

class FavoritesRepository(private val gateWay: PostsGateWay) {
}