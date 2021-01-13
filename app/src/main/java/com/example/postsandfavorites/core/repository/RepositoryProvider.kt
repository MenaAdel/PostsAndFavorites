package com.example.postsandfavorites.core.repository

import com.example.postsandfavorites.domain.posts.repository.PostsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { PostsRepository(get(), get()) }
}