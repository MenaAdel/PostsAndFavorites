package com.example.postsandfavorites.core.dataloading

import io.reactivex.rxjava3.core.Single

fun <T> loadRemoteData(
    remote: Single<Data<T>>,
    local: Single<Data<T>>,
    saveData: (data: T) -> Unit
): Single<Data<T>> {
    return loadDataFromRemote(remote, saveData).onErrorResumeNext { local }
}

private fun <T> loadDataFromRemote(
    remote: Single<Data<T>>,
    saveData: (data: T) -> Unit
): Single<Data<T>> {
    return remote.doOnSuccess { saveData(it.data) }
}


sealed class Data<T>(val data: T)
class LocalData<T>(data: T) : Data<T>(data)
class RemoteData<T, Extra>(data: T, val extra: Extra? = null) : Data<T>(data)