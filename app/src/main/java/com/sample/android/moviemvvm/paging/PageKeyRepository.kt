package com.sample.android.moviemvvm.paging

import androidx.lifecycle.Transformations
import androidx.annotation.MainThread
import androidx.paging.LivePagedListBuilder
import com.sample.android.moviemvvm.domain.TmdbItem
import java.util.concurrent.Executor

abstract class PageKeyRepository<T : TmdbItem> : TmdbPageKeyRepository<T> {

    protected abstract fun getSourceFactory(retryExecutor: Executor): TmdbDataSourceFactory<T>

    @MainThread
    override fun getItems(networkExecutor: Executor): Listing<T> {

        val sourceFactory = getSourceFactory(networkExecutor)

        val livePagedList = LivePagedListBuilder(sourceFactory, 20)
                // provide custom executor for network requests, otherwise it will default to
                // Arch Components' IO pool which is also used for disk access
                .setFetchExecutor(networkExecutor)
                .build()

        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.initialLoad
        }

        val networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.networkState
        }

        return Listing(
                pagedList = livePagedList,
                networkState = networkState,
                refresh = {
                    sourceFactory.sourceLiveData.value?.invalidate()
                },
                refreshState = refreshState,
                retry = {
                    sourceFactory.sourceLiveData.value?.retryAllFailed()
                }
        )
    }
}