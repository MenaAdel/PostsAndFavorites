package com.example.postsandfavorites.entites.posts

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "Post")
//@TypeConverters(TypesConverter::class)
data class PostsResponse (
    @PrimaryKey
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)