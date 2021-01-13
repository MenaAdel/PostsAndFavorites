package com.example.postsandfavorites.core.database.converter

import androidx.room.TypeConverter
import com.example.postsandfavorites.entites.posts.PostsResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/*
class TypesConverter {
    @TypeConverter
    fun getPostsListState(json: String?): PostsResponse? {
        val type = object : TypeToken<List<PostsResponse>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun getPostsListStateJson(list: List<PostsResponse>): String? {
        val type = object : TypeToken<List<PostsResponse>>() {}.type
        return Gson().toJson(list, type)
    }
}*/
