package com.example.postsandfavorites.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.postsandfavorites.entites.posts.PostsResponse
import io.reactivex.rxjava3.core.Single

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePosts(list: List<PostsResponse>)

    @Query("Select * From Post")
    fun loadPosts(): List<PostsResponse>
}