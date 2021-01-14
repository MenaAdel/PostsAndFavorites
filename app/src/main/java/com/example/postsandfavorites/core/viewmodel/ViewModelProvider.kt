package com.example.postsandfavorites.core.viewmodel

import com.example.postsandfavorites.presentation.details.CommentsViewModel
import com.example.postsandfavorites.presentation.posts.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelProvider = module {
    viewModel { PostsViewModel(get()) }
    viewModel { CommentsViewModel(get()) }
}