package com.example.postsandfavorites.entites.comments


data class Comment(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val email: String
)