package com.example.postsandfavorites

import android.app.Application
import com.example.postsandfavorites.core.database.roomModule
import com.example.postsandfavorites.core.network.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    retrofitModule ,roomModule
                )
            )
        }
    }
}