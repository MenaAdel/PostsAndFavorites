package com.example.postsandfavorites.presentation.favorits

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.*
import com.example.postsandfavorites.core.extentions.RxCommons
import com.example.postsandfavorites.core.extentions.RxCommonsProd
import com.example.postsandfavorites.core.extentions.disposedBy
import com.example.postsandfavorites.core.workmanager.UploadWorker
import com.example.postsandfavorites.domain.favorites.repository.FavoritesRepository
import com.example.postsandfavorites.entites.comments.Comment
import io.reactivex.rxjava3.disposables.CompositeDisposable

class FavoritesViewModel(val repository: FavoritesRepository ,val applicationContext: Context ,val rxCommons: RxCommons = RxCommonsProd()) : ViewModel() {


    internal val screenState by lazy { MutableLiveData<FavsStates>() }
    private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    fun saveDataToServer() {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .setRequiresStorageNotLow(true)
            .build()

        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .addTag(UploadWorker::class.java.simpleName)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueueUniqueWork(
                UploadWorker::class.java.simpleName,
                ExistingWorkPolicy.KEEP,
                oneTimeWorkRequest
            )
    }

    fun getPosts(postId: Int) {
        screenState.postValue(FavsStates.Loading)
        repository.postFavorites(postId)
            .compose(rxCommons::backgroundComposer)
            .map (::responseToSuccessState)
            .onErrorReturn { errorState(it) }
            .subscribe(screenState::postValue, Throwable::printStackTrace)
            .disposedBy(disposable)
    }
    private fun errorState(throwable: Throwable): FavsStates = FavsStates.Error
    private fun responseToSuccessState(list: List<Comment>): FavsStates = FavsStates.Success(list)



    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}


sealed class FavsStates {
    object Loading : FavsStates()
    data class Success(val data: List<Comment>) : FavsStates()
    object Error : FavsStates()
}