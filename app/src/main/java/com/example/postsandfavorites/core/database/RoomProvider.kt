package com.example.postsandfavorites.core.database

import android.app.Application
import androidx.room.Room
import com.example.postsandfavorites.core.database.dao.PostsDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {
    fun provideRoom(application: Application): DataBase {
        return Room.databaseBuilder(application, DataBase::class.java, "posts.db")
            .allowMainThreadQueries()
            .build()
    }

    fun provideDao(dataBase: DataBase): PostsDao =
        dataBase.postDao()

    single { provideRoom(androidApplication()) }
    single { provideDao(get()) }
}