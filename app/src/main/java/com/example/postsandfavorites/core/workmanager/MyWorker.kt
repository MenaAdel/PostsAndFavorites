package com.example.postsandfavorites.core.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.postsandfavorites.core.database.preferences.AppPreference
import com.example.postsandfavorites.presentation.favorits.FavoritesViewModel

class UploadWorker(private val context: Context ,private val workerParameters: WorkerParameters ,val viewModel: FavoritesViewModel): Worker(context ,workerParameters) {



    override fun doWork(): Result {
        postLikeValues()
        return Result.success()
    }

    fun postLikeValues() {
        val arr = AppPreference(context).getIntArray("ARR")
        arr.forEach {
            viewModel.getPosts(it)
        }
    }
}
