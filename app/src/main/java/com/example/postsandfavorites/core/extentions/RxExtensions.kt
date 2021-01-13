package com.example.postsandfavorites.core.extentions

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

fun Disposable.disposedBy(disposables: CompositeDisposable) {
    disposables.add(this)
}

interface RxCommons {
    fun <T> backgroundComposer(observable: Observable<T>): Observable<T>

    fun <T> backgroundComposer(single: Single<T>): Single<T>

    fun backgroundComposer(completable: Completable): Completable
}

class RxCommonsProd : RxCommons {
    override fun <T> backgroundComposer(observable: Observable<T>): Observable<T> =
        observable.observeOn(Schedulers.io()).subscribeOn(Schedulers.io())

    override fun <T> backgroundComposer(single: Single<T>): Single<T> =
        single.observeOn(Schedulers.io()).subscribeOn(Schedulers.io())

    override fun backgroundComposer(completable: Completable): Completable =
        completable.observeOn(Schedulers.io()).subscribeOn(Schedulers.io())
}

class RxCommonsTest : RxCommons {
    override fun <T> backgroundComposer(observable: Observable<T>): Observable<T> =
        observable.observeOn(Schedulers.trampoline()).subscribeOn(Schedulers.trampoline())

    override fun <T> backgroundComposer(single: Single<T>): Single<T> =
        single.observeOn(Schedulers.trampoline()).subscribeOn(Schedulers.trampoline())

    override fun backgroundComposer(completable: Completable): Completable =
        completable.observeOn(Schedulers.trampoline()).subscribeOn(Schedulers.trampoline())
}