package com.sample.android.moviemvvm.paging

import com.sample.android.moviemvvm.domain.TmdbItem
import java.util.concurrent.Executor

interface TmdbPageKeyRepository<T : TmdbItem> {
    fun getItems(networkExecutor: Executor): Listing<T>
}