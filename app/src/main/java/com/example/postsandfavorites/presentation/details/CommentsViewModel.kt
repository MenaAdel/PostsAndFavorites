package com.example.postsandfavorites.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.postsandfavorites.core.extentions.RxCommons
import com.example.postsandfavorites.core.extentions.RxCommonsProd
import com.example.postsandfavorites.core.extentions.disposedBy
import com.example.postsandfavorites.domain.comment.repository.CommentRepository
import com.example.postsandfavorites.entites.comments.Comment
import io.reactivex.rxjava3.disposables.CompositeDisposable

class CommentsViewModel(private val commentRepository: CommentRepository) : ViewModel() {

    internal val screenState by lazy { MutableLiveData<CommentsStates>() }
    private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    fun getPosts(rxCommons: RxCommons = RxCommonsProd() ,postId: Int) {
        screenState.postValue(CommentsStates.Loading)
        commentRepository.getComments(postId)
            .compose(rxCommons::backgroundComposer)
            .map (::responseToSuccessState)
            .onErrorReturn { errorState(it) }
            .subscribe(screenState::postValue, Throwable::printStackTrace)
            .disposedBy(disposable)
    }

    private fun responseToSuccessState(list: List<Comment>): CommentsStates = CommentsStates.Success(list)
    private fun errorState(throwable: Throwable): CommentsStates = CommentsStates.Error

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

}

sealed class CommentsStates {
    object Loading : CommentsStates()
    data class Success(val data: List<Comment>) : CommentsStates()
    object Error : CommentsStates()
}