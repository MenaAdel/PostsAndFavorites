package com.example.postsandfavorites.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.postsandfavorites.core.database.dao.PostsDao
import com.example.postsandfavorites.entites.posts.PostsResponse

@Database(entities = [PostsResponse::class] ,version = 1 ,exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun postDao(): PostsDao
}