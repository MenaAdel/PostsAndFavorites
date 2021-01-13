package com.example.postsandfavorites.domain.posts.repository

import com.example.postsandfavorites.core.database.dao.PostsDao
import com.example.postsandfavorites.core.dataloading.Data
import com.example.postsandfavorites.core.dataloading.LocalData
import com.example.postsandfavorites.core.dataloading.RemoteData
import com.example.postsandfavorites.core.dataloading.loadRemoteData
import com.example.postsandfavorites.domain.posts.datasource.remote.PostsGateWay
import com.example.postsandfavorites.entites.posts.PostsResponse
import io.reactivex.rxjava3.core.Single

class PostsRepository (val gateWay: PostsGateWay) {

    private lateinit var dao: PostsDao
    constructor( gateWay: PostsGateWay,  dao: PostsDao):this(gateWay) {
        this.dao = dao
    }

    fun getPosts(): Single<Data<List<PostsResponse>>> {
        return loadRemoteData<List<PostsResponse>>(
            remote = gateWay.getPosts().map { RemoteData(data = it ,null) },
            local = Single.fromCallable{
                dao.loadPosts()
            }.map { LocalData(it) },
            saveData = { data -> dao.savePosts(data) }
        )
    }
}