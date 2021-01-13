package com.example.postsandfavorites

import android.app.Application
import com.example.postsandfavorites.core.database.roomModule
import com.example.postsandfavorites.core.gateway.gatewayProvider
import com.example.postsandfavorites.core.network.retrofitModule
import com.example.postsandfavorites.core.repository.repositoryModule
import com.example.postsandfavorites.core.viewmodel.viewModelProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    roomModule, repositoryModule, viewModelProvider, retrofitModule, gatewayProvider)

            )
        }
    }
}