package com.example.postsandfavorites.core.database

import androidx.room.RoomDatabase
import com.example.postsandfavorites.core.database.dao.CommentsDao

abstract class DataBase : RoomDatabase() {
    abstract fun commentDao(): CommentsDao
}