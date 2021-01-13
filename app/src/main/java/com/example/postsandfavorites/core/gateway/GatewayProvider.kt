package com.example.postsandfavorites.core.gateway

import com.example.postsandfavorites.domain.posts.datasource.remote.PostsGateWay
import org.koin.dsl.module
import retrofit2.Retrofit

val gatewayProvider = module {
    fun providePostGateway(retrofit: Retrofit): PostsGateWay {
        return retrofit.create(PostsGateWay::class.java)
    }
    single { providePostGateway(get()) }
}