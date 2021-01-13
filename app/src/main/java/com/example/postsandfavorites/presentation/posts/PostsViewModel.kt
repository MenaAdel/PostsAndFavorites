package com.example.postsandfavorites.presentation.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.postsandfavorites.core.dataloading.LocalData
import com.example.postsandfavorites.core.dataloading.RemoteData
import com.example.postsandfavorites.core.extentions.RxCommons
import com.example.postsandfavorites.core.extentions.RxCommonsProd
import com.example.postsandfavorites.core.extentions.disposedBy
import com.example.postsandfavorites.domain.posts.repository.PostsRepository
import com.example.postsandfavorites.entites.posts.PostsResponse
import io.reactivex.rxjava3.disposables.CompositeDisposable

class PostsViewModel(val postsRepository: PostsRepository) : ViewModel() {

    internal val screenState by lazy { MutableLiveData<PostsStates>() }
    private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    fun getPosts(rxCommons: RxCommons = RxCommonsProd()) {
        screenState.postValue(PostsStates.Loading)
        postsRepository.getPosts()
            .compose(rxCommons::backgroundComposer)
            .subscribe({
                when (it) {
                    is LocalData -> screenState.postValue(PostsStates.LocalSuccess(it.data))
                    is RemoteData<*, *> -> screenState.postValue(PostsStates.RemoteSuccess(it.data))
                }
            }, { screenState.postValue(PostsStates.Error) })
            .disposedBy(disposable)
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}

sealed class PostsStates {
    object Loading : PostsStates()
    data class RemoteSuccess(val data: List<PostsResponse>) : PostsStates()
    data class LocalSuccess(val data: List<PostsResponse>) : PostsStates()
    object Error : PostsStates()
}